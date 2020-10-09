package cn.xiaoge.design.cacheAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class CacheAspect {

    private final static String PROJECT_NAME ="design";

    @Autowired
    private RedisTemplate redisTemplate;

    //aop切面
    @Pointcut("execution(* cn.xiaoge.design.service.*.*(..))")
    public void pointcutName() {
    }

    @Around("pointcutName()")
    public Object performance(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("cacheAop");
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Class declaringType = signature.getDeclaringType();

        MyCacheable myCacheable = targetMethod.getAnnotation(MyCacheable.class);

        MyCacheEvict myCacheEvict = targetMethod.getAnnotation(MyCacheEvict.class);
        //清除缓存
        if (myCacheEvict != null) {
            boolean allEntries = myCacheEvict.allEntries();
            if (allEntries) {
                String key = PROJECT_NAME + "_" + declaringType.getSimpleName() + "_*";
                Set deleteKeys = redisTemplate.keys(key);
                for (Object deleteKey : deleteKeys) {
                    redisTemplate.delete(deleteKey);
                }
                String[] classNames = myCacheEvict.classNames();
                for (String className : classNames) {
                    Set keys = redisTemplate.keys(PROJECT_NAME + "_" + className + "_*");
                    for (Object deleteKey : keys) {
                        redisTemplate.delete(deleteKey);
                    }
                }
            } else {
                String[] keys = myCacheEvict.methodNames();
                for (String s : keys) {
                    String key = PROJECT_NAME + "_" + declaringType.getSimpleName() + "_" + s + "*";
                    Set deleteKeys = redisTemplate.keys(key);
                    for (Object deleteKey : deleteKeys) {
                        redisTemplate.delete(deleteKey);
                    }
                }
            }

        }
        //缓存
        if (myCacheable == null) {
            //没有注解的直接通过
            return pjp.proceed();
        } else {
            boolean endTheNight = myCacheable.endTheNight();
            boolean nullCache = myCacheable.nullCache();
            int survivalTime = myCacheable.survivalTime();
            Parameter[] parameters = targetMethod.getParameters();
            StringBuffer stringBuffer = new StringBuffer();
            Object[] args = pjp.getArgs();
            for (int i = 0; i < parameters.length; i++) {
                stringBuffer.append("_" + parameters[i].getName() + args[i]);
            }
            //键生成 项目名_类名_方法名_参数名+参数_参数名+参数
            String key = PROJECT_NAME + "_" + declaringType.getSimpleName() + "_" + methodSignature.getName() + stringBuffer;
            Object cacheData = redisTemplate.opsForValue().get(key);
            if (cacheData == null) {
                Object proceed = pjp.proceed();
                if (proceed == null && !nullCache) {
                    return null;
                }
                redisTemplate.opsForValue().set(key, proceed);
                if (endTheNight) {
                    //今夜结束
                    redisTemplate.expire(key, getSecondsNextEarlyMorning(), TimeUnit.SECONDS);
                    return proceed;
                } else {
                    //使用指定时长
                    redisTemplate.expire(key, survivalTime, TimeUnit.SECONDS);
                    return proceed;
                }
            }
            return cacheData;
        }
    }

    //获取到第二天凌晨的秒数
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

}