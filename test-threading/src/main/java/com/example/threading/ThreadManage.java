package com.example.threading;

import java.util.List;

/**
 * @author xboat date 2019-01-05
 */
public class ThreadManage<T> {
    public synchronized void handleList(List<T> data, int threadNum, ThreadCallBack threadCallBack) {
        long startTimeMillis = System.currentTimeMillis();
        int length = data.size();
        int tl = length % threadNum == 0 ? length / threadNum : (length
                / threadNum + 1);

        for (int i = 0; i < threadNum; i++) {
            int end = (i + 1) * tl;
            HandleThread thread = new HandleThread("线程[" + (i + 1) + "] ",  data, i * tl, end > length ? length : end,threadCallBack);
            thread.start();
        }
        long endTimeMillis = System.currentTimeMillis();
        System.out.println("ThreadManage 耗时------>:"+(endTimeMillis-startTimeMillis)+"ms");
    }

    class HandleThread implements Runnable{
        private java.lang.Thread thread;
        private String threadName;
        private List<T> data;
        private int start;
        private int end;
        private ThreadCallBack threadCallBack;

        private HandleThread(String threadName, List<T> data, int start, int end,ThreadCallBack threadCallBack) {
            this.threadName = threadName;
            this.data = data;
            this.start = start;
            this.end = end;
            this.threadCallBack=threadCallBack;
        }

        @Override
        public void run() {
            List<T> subList = data.subList(start, end);
            System.out.println(threadName+"处理了"+subList.size()+"条");
            this.threadCallBack.process(subList);
        }

        private void start () {
            if (thread == null) {
                thread = new java.lang.Thread(this, threadName);
                thread.start ();
            }
        }
    }
}
