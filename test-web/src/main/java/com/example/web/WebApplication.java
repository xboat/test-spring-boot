package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2018-12-10
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
        System.out.println("<---test---->");
    }
}
