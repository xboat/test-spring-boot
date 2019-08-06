package com.example.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author xboat date 2019-07-28
 */
@SpringBootApplication
@EnableScheduling
public class ScheduleApplication implements CommandLineRunner {
    private static final Logger LOGGER =  LoggerFactory.getLogger(ScheduleApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
        System.out.println("<---start scheduling---->");
    }

    @Override
    public void run(String... var1){
        System.out.println("test");
    }

    @Component
    public class ScheduledTasks{
        private Integer num = 1;
        @Scheduled(cron="1/3 * * * * ?")
        public void work() {
            num++;
            LOGGER.info("work--->"+num);

        }
    }

}


