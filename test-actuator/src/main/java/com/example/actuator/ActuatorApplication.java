package com.example.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2019-03-05
 */
@SpringBootApplication
public class ActuatorApplication {

    public static void main(String[] args){
        SpringApplication.run(ActuatorApplication.class, args);
        System.out.println("<---start actuator---->");
    }
}
