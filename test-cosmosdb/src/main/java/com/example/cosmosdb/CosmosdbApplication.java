package com.example.cosmosdb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author xboat date 2019-12-14
 */
@SpringBootApplication
public class CosmosdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmosdbApplication.class, args);
        System.out.println("<---start cosmosdb---->");
    }
}
