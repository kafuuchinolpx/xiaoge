package cn.xiaoge.design.util;

import org.springframework.data.redis.core.RedisTemplate;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private RedisUtil(){
    }

    private final static String projectName="design";

    /**
     * 清空项目全部缓存
     */
    public static void deleteAll() {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        String keys = projectName + "_*";
        Set deleteKeys = redisTemplate.keys(keys);
        for (Object deleteKey : deleteKeys) {
            redisTemplate.delete(deleteKey);
        }
    }

    /**
    * 设置值
    *
    * @param key   键
    * @param value 值
    */
    public static void put(String key, Object value) {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        String keys = projectName + "_" + key;
        redisTemplate.opsForValue().set(keys, value);
    }



    /**
    * 获取值
    *
    * @param key 键
    * @param t 响应类型
    * @return 值
    */
    public static <T> T get(String key) {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        String keys = projectName + "_" + key;
        Object o = redisTemplate.opsForValue().get(keys);
        return (T) o;
    }


    /**
     * 删除键
     * @param key 键
     */
    public static void delete(String key) {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        String keys = projectName + "_" + key;
        redisTemplate.delete(keys);
    }

    /**
     * 设置键的过期时间
     * @param key      键
     * @param time     时间
     * @param timeUnit 单位
     */
    public static void expire(String key, long time, TimeUnit timeUnit) {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        String keys = projectName + "_" + key;
        redisTemplate.expire(keys, time, timeUnit);
    }

    /**
    * 加锁
    * @param key    redis key
    * @return true:加锁成功，false，加锁失败
    */
    public static boolean lock(String key)  throws InterruptedException {
        return lock(key, 60);
    }

    /**
    * 加锁
    * @param key         键
    * @param expire      超时时间 单位秒
    * @return true:加锁成功，false，加锁失败
    */
    public static boolean lock(String key, int expire) throws InterruptedException {
        RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        boolean result = redisTemplate.opsForValue().setIfAbsent(projectName + "_lock_" + key, "true", expire, TimeUnit.SECONDS);
        for (int i = 0; (i < expire && (!result)); i++) {
            System.out.println("等待。。");
            Thread.sleep(1000);
            result = redisTemplate.opsForValue().setIfAbsent(projectName + "_lock_" + key, "true", expire, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * 解锁
     * @param key 键
     */
    public static void unLock(String key) {
        delete("_lock_" + key);
    }

}
