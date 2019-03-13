package com.example.starter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xboat date 2019-03-05
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfigure {
    @Autowired
    private DubboProperties properties;

    @Bean
    @ConditionalOnClass({DubboService.class}) //当classpath下存在该类进行自动配置
    @ConditionalOnMissingBean({DubboService.class})//当Spring Context中不存在该Bean时
    //当配置文件中dubbo.enabled=true 时
    @ConditionalOnProperty(prefix = "dubbo", value = "enabled", havingValue = "true")
    DubboService exampleService() {
        System.out.println("dubbo");
        return new DubboService(properties);
    }
}