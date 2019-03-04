package com.example.dubbo.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2019-‎02-‎20
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.example.dubbo.provider")
//@ImportResource(value = {"classpath:providers.xml"})
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
        System.out.println("<---start dubbo provider ---->");
    }
}
