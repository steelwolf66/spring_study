package com.zhouyu.thread.mythreadpool;


import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Worker> workers = new HashSet<>();
    //核心线程数
    private int coreSize;
    //获取任务时的超时时间
    private long timeout;
    //时间单位
    private TimeUnit timeUnit;
    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    public MyThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    System.out.println("线程:" + Thread.currentThread().getName() + "执行任务:" + task.toString());
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                System.out.println("线程:" + Thread.currentThread().getName() + "退出线程池");
                workers.remove(this);
            }
        }
    }
}
