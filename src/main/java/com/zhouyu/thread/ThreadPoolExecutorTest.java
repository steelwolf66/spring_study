package com.zhouyu.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 50, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(950), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(() -> {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());


            });
        }
        threadPoolExecutor.shutdown();
    }
}