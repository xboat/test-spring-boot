package com.example.events;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
/**
 * @author xboat date 2019-01-14
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SpringContextUtils {
    private static ConfigurableApplicationContext applicationContext;

    //获取上下文
    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //设置上下文
    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    //通过名字获取上下文中的bean
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static Object getBeanByClass(Class cls){
        return applicationContext.getBean(cls);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requiredType){
        return applicationContext.getBeansOfType(requiredType);
    }
}
