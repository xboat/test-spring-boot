package com.example.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author xboat date 2019-01-05
 */
@Component
public class TestService {
    @HystrixCommand(fallbackMethod = "fallbackGetName", commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10")
    })
    public String getName(){
        //throw new RuntimeException("RuntimeException");
        return "hello word";
    }

    private String fallbackGetName(){
        return "fallbackMethod";
    }


    @HystrixCommand(fallbackMethod = "fallbackGetNameAsync", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Future<String> getNameAsync(String name) {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // throw new RuntimeException("RuntimeException");
                return "getNameAsync"+name;
            }
        };
    }

    @HystrixCommand
    String fallbackGetNameAsync(String name) {
        return "fallbackGetNameAsync"+name;
    }
}
