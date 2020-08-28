package cn.xiaoge.design.cacheAop;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheEvict {

    boolean allEntries() default false;//清除该类的全部缓存  与methodNames二选一即可

    String[] methodNames() default {};//清除指定的方法的缓存  与allEntries二选一即可

    String[] classNames() default {};//清除指定的类的缓存  值为类名（首字母大写）

}
