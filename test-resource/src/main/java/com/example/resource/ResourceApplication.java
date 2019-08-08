package com.example.resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;


/**
 * @author xboat date 2019-08-07
 */
@SpringBootApplication
public class ResourceApplication implements CommandLineRunner {

    private static final String FILE_NAME ="com.example.resource";

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
        System.out.println("<---start resource---->");
    }

    private UserService userService;

    @Override
    public void run(String... var1){
        String serviceName = getServiceName();
        try {
            // 加载类
            Class<UserService> aClass = (Class<UserService>) Class.forName(serviceName);
            try {
                //实例化
                userService = aClass.newInstance();
                userService.setUserName("刘备");
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String userName =userService.getUserName();
        System.out.println("姓名-->"+userName);
    }

    private static String getServiceName(){
        Set<String> set = ResourceUtil.getPropertyKeySet(FILE_NAME);
        if (set != null && ((Set) set).size() > 0) {
            for (String key : set) {
                 return ResourceUtil.getProperty(FILE_NAME,key);
            }
        }
        return "";
    }


}


