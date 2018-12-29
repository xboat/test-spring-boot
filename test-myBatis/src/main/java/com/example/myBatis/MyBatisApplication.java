package com.example.myBatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author xboat date 2018-12-10
 */

@SpringBootApplication
@MapperScan(basePackages = "com.example.myBatis.mapper")
public class MyBatisApplication {
    public static void main(String[] args){
        SpringApplication.run(MyBatisApplication.class, args);
        System.out.println("<---start myBatis---->");
    }
}
