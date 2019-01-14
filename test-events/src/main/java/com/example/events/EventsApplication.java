package com.example.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xboat date 2019-01-14
 */
@SpringBootApplication
public class EventsApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EventsApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext);
        System.out.println("<---start  events---->");
    }
}
