package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author xboat date 2019-01-16
 */

@SpringBootApplication
public class SecurityApplication{

    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class, args);
        System.out.println("<---start security---->");
    }
}
