package com.example.web;

import com.example.web.configuration.Swagger2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * @author xboat date 2018-12-10
 */
//@EnableConfigurationProperties(value={Swagger2Config.class })
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
        System.out.println("<---test---->");
    }
}
