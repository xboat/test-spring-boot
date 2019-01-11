package com.example.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Future;

/**
 * @author xboat date 2019-01-05
 */
@SpringBootApplication
public class HystrixApplication implements CommandLineRunner {

    @Autowired
    private TestService testService;

    public static void main(String[] args){
        SpringApplication.run(HystrixApplication.class, args);
        System.out.println("<---start hystrix---->");
    }

    @Override
    public void run(String... strings) throws Exception {
        Future<String> stringFuture=testService.getNameAsync("test");
        System.out.println("getNameAsync---->"+stringFuture.get());
        String result=testService.getName();
        System.out.println("getName---->"+result);
    }
}
