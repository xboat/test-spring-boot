package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2019-01-04
 */
@SpringBootApplication
public class ConfigApplication implements CommandLineRunner {

    @Autowired
    private BookProperties bookProperties;
    public static void main(String[] args){
        SpringApplication.run(ConfigApplication.class, args);
        System.out.println("<---start config---->");
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("yml --------");
        System.out.println("name--->"+bookProperties.getName());
        System.out.println("page--->"+bookProperties.getPage());
        System.out.println("stauts--->"+bookProperties.getStatus());
        System.out.println("date--->"+bookProperties.getDate());
        System.out.println("list--->"+bookProperties.getLists().get(0));
        System.out.println("maps--->"+bookProperties.getMaps().get("key1"));
        System.out.println("height--->"+bookProperties.getImageInfo().getHeight());
    }
}
