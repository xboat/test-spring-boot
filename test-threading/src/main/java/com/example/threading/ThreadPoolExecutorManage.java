package com.example.threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xboat date 2019-01-05
 */
public class ThreadPoolExecutorManage<T> {

     class HandleThread implements Runnable {
        private List<T> data;
        private ThreadCallBack threadCallBack;
        private HandleThread(List<T> data, ThreadCallBack threadCallBack) {
            this.data=data;
            this.threadCallBack=threadCallBack;
        }
        @Override
        public void run() {
            try {
                this.threadCallBack.process(data);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void handle(List<T> data,ThreadCallBack threadCallBack) {
        ExecutorService executor = new ThreadPoolExecutor(
                5,
                10,
                100L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(30),
                new ThreadPoolExecutor.CallerRunsPolicy());

        int size =data.size();
        for (int i = 0; i < size; i++) {
            int end = i + 10;
            end = end > size?size:end;
            System.out.println("-->"+i+"||"+end);
            List<T> list =data.subList(i,end);
            System.out.println("list-->"+list);
            executor.submit(new HandleThread(list, threadCallBack));
            i =end-1;
        }
        executor.shutdown();
    }
}
