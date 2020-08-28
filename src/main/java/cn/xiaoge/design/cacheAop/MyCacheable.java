package cn.xiaoge.design.cacheAop;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheable {

    boolean nullCache() default false;//返回结果为空是否缓存

    int survivalTime() default 24 * 3600;//存活时间 默认一天 单位秒

    boolean endTheNight() default false;// 晚上消失  如果为true 存活时间可以不设置

}
