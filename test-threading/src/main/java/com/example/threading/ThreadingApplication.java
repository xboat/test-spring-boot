package com.example.threading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xboat date 2019-01-05
 */
@SpringBootApplication
public class ThreadingApplication {
    public static void main(String[] args){
        SpringApplication.run(ThreadingApplication.class, args);
        System.out.println("<---start  threading---->");
        testThreadPool();
    }


    public static void testThreadPool() {
        List<String> list= new LinkedList<String>();
        for (int i = 0; i < 100; i++) {
            list.add((i+1)+"");
        }
        long startNanoTime = System.nanoTime();
        ThreadPoolExecutorManage<String> thread=new ThreadPoolExecutorManage();
        thread.handle(list, new ThreadCallBack() {
            @Override
            public <T> void process(List<T> list) {
                System.out.println("回调："+list.size());
            }
        });
        long endNanoTime = System.nanoTime();
        //以纳秒为单位计算的
        System.out.println("testThreadPool 耗时------>:"+((endNanoTime-startNanoTime))+"ns");
    }

    public static void testThread() {
        List<String> list= new LinkedList<String>();
        for (int i = 0; i < 100998; i++) {
            list.add(getNumber());
        }
        long startNanoTime = System.nanoTime();
        ThreadManage<String> thread=new ThreadManage();
        thread.handleList(list, 5, new ThreadCallBack() {
            @Override
            public <T> void process(List<T> list) {
                System.out.println("回调："+list.size());
            }
        });

        long endNanoTime = System.nanoTime();
        //以纳秒为单位计算的
        System.out.println("testThread 耗时------>:"+((endNanoTime-startNanoTime))+"ns");
    }


    private static String getNumber() {
        int random = (int)(Math.random());
        return String.valueOf(random+10000);
    }
}
