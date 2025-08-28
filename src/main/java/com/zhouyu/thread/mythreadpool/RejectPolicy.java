package com.zhouyu.thread.mythreadpool;

//拒绝策略
@FunctionalInterface
public interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
