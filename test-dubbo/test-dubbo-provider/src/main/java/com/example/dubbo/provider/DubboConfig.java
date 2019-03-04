package com.example.dubbo.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xboat date 2019-‎03-‎04
 */
@Configuration
public class DubboConfig {

    /**
     * 当前应用配置
     * @return
     */
    @Bean
    public ApplicationConfig setApplicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-provider");
        applicationConfig.setLogger("slf4j");
        return applicationConfig;
    }

    /**
     * 服务提供者协议配置
     * @return
     */
    @Bean
    public ProtocolConfig setProtocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }

    /**
     * 连接注册中心配置
     * @return
     */
    @Bean
    public RegistryConfig setRegistryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("zookeeper://192.168.231.134:2181?backup=192.168.231.134:2181");
        //registryConfig.setUsername("aaa");
        //registryConfig.setPassword("bbb");
        registryConfig.setRegister(true);
        //registryConfig.setSubscribe(true);
        return registryConfig;
    }

}