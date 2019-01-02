package com.example.web;

import com.example.web.annotation.EnableWebApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2018-12-10
 */
//@EnableConfigurationProperties(value={Swagger2Config.class })
@EnableWebApi
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
        System.out.println("<---test---->");
    }
}
