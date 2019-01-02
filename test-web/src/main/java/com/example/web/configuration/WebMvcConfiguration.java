package com.example.web.configuration;

import com.example.web.registrar.AbstractBeanRegistrar;
import com.example.web.registrar.BeanDefinition;

/**
 * @author xboat date 2019-01-02
 */
public class WebMvcConfiguration extends AbstractBeanRegistrar {

    @Override
    public void registerBeans() {
        System.out.println("WebMvcConfiguration --> registerBeans");
        registerBeanDefinition(
                BeanDefinition.newInstance(WebMvcConfig.class).setBeanName("WebMvcConfig")
                        .addPropertyValue("name",environment.getProperty("Swagger2Config.name"))
        );
    }
}
