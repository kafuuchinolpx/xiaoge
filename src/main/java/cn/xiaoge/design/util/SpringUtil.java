package cn.xiaoge.design.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
public class SpringUtil implements ApplicationContextAware {
    private SpringUtil(){
    }
    private static ApplicationContext applicationContext;

    /**
     * 设置上下文容器
     * @param applicationContext 上下文容器
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取上下文容器
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取对象
     * @param name 名称
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据类型获取对象
     * @param clazz 类型
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据名称和类型获取对象
     * @param name 名称
     * @param clazz 类型
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
    * 获取spring容器中配置文件的值
    * @param path
    * @return
    */
    public String getProperty(String path) {
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getProperty(path);
    }
} 