package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xboat date 2019-01-03
 */
@SpringBootApplication
public class LoggingApplication {
    private static final Logger logger = LoggerFactory.getLogger(LoggingApplication.class);
    public static void main(String[] args){
        SpringApplication.run(LoggingApplication.class, args);

        logger.info("info");
        logger.debug("debug");
        logger.error("error");
        logger.warn("warn");
        System.out.println("<---start logging---->");

    }
}
