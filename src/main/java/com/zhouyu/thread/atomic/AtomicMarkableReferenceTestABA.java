package com.zhouyu.thread.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceTestABA {
    public static void main(String[] args) throws InterruptedException {
        GarbageBag bag = new GarbageBag("装满了");
        //参数2 mark,可以看做一个标记，表示垃圾袋装满了
        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference<>(bag, true);
        System.out.println("主线程");
        GarbageBag prev = ref.getReference();
        System.out.println(prev.toString());

        new Thread(() -> {
            System.out.println("清理垃圾线程");
            bag.setDesc("空垃圾袋");
            while (!ref.compareAndSet(bag, bag, true, false)) {

            }
            System.out.println(bag.toString());
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程 换一只新垃圾袋");

        boolean mainSuccess = ref.compareAndSet(prev, new GarbageBag("空垃圾袋"), true, false);
        System.out.println("主线程是否更换成功:" + mainSuccess);

        System.out.println(ref.getReference().toString());

    }
}
