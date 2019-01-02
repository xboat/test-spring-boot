package com.example.web.registrar;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xboat date 2019-01-02
 */
public class BeanDefinition<T> {

    private Class<T> beanClass;
    private String beanName;
    private Map<String,Object> properties;

    private BeanDefinition(){}

    public static <T> BeanDefinition<T> newInstance(Class<T> t){
        BeanDefinition<T> beanDefinition = new BeanDefinition<>();
        beanDefinition.setBeanClass(t);
        return beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanDefinition setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public Class<T> getBeanClass(){
        return beanClass;
    }

    public BeanDefinition setBeanClass(Class<T> beanClass){
        this.beanClass=beanClass;
        return this;
    }

    public BeanDefinition addPropertyValue(String name, Object value){
        if(value==null){
            return this;
        }
        if(properties==null){
            properties = new HashMap<>();
        }
        properties.put(name,value);
        return this;
    }

    public Map<String,Object> getProperties() {
        return properties;
    }
}
