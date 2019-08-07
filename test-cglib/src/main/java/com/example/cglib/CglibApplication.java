package com.example.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * @author xboat date 2019-08-06
 */
@SpringBootApplication
public class CglibApplication implements CommandLineRunner {

    private static final Logger LOGGER =  LoggerFactory.getLogger(CglibApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CglibApplication.class, args);
        System.out.println("<---start cglib---->");
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... var1){
        String userName =userService.getUserName();
        System.out.println("姓名-->"+userName);
    }

    @Bean
    public UserService userService(){
        UserService proxyObj = null;
        try {
            proxyObj = getProxyObj(UserService.class);
        } catch (Exception e) {
            LOGGER.error("getProxyObj 动态代理失败");
        }
        assert proxyObj != null;
        proxyObj.setUserName("张飞");
        return proxyObj;
    }

    private static <T> T getProxyObj(Class<T> cla) throws Exception {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(cla);
        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                doBefore();
                Object result = methodProxy.invokeSuper(o,objects);
                doAfter();
                return result;
            }
        });
        enhancer.setClassLoader(cla.getClassLoader());
        return (T) enhancer.create();
    }

    private static void doBefore() {
        System.out.println("doBefore");
    }

    private static void doAfter() {
        System.out.println("doAfter");
    }



}


