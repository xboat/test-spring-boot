package com.example.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.example.dubbo.DemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2019-‎02-‎20
 */
@SpringBootApplication
@EnableDubbo
public class ConsumerApplication implements CommandLineRunner {

    @Reference
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
        System.out.println("<---start dubbo consumer ---->");
    }

    @Override
    public void run(String... strings) throws Exception {
     String message = demoService.sayHello("dubbo");
        System.out.println("message--->"+message);
    }
}
