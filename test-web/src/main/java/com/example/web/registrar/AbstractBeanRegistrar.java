package com.example.web.registrar;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * @author xboat date 2019-01-02
 */
public abstract class AbstractBeanRegistrar implements ApplicationContextAware,BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    protected ApplicationContext applicationContext;
    protected BeanDefinitionRegistry registry;
    protected ConfigurableEnvironment environment;

    public abstract void registerBeans();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.registry = beanDefinitionRegistry;
        registerBeans();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    public boolean registerBeanDefinition(BeanDefinition beanDefinition) {
        String beanName = beanDefinition.getBeanName()==null?beanDefinition.getBeanClass().getName():beanDefinition.getBeanName();
        if (registry.containsBeanDefinition(beanName)) {
            return false;
        }
        String[] candidates = registry.getBeanDefinitionNames();
        for (String candidate : candidates) {
            org.springframework.beans.factory.config.BeanDefinition springBeanDefinition = registry.getBeanDefinition(candidate);
            if (Objects.equals(springBeanDefinition.getBeanClassName(), beanDefinition.getBeanClass().getName())
                    &&Objects.equals(candidate,beanName)) {
                return false;
            }
        }
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition( beanDefinition.getBeanClass());
        if (beanDefinition != null) {
            if (beanDefinition.getProperties() != null) {
                beanDefinition.getProperties().forEach((name,value) -> beanDefinitionBuilder.addPropertyValue((String)name, value));
            }
        }
        registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        return true;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment= (ConfigurableEnvironment) environment;
    }

    public String getProperty(String key){
        return environment.getProperty(key);
    }
}
