package com.zhouyu.thread.atomic;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1L);
        System.out.println(longAdder);
        longAdder.sumThenReset();
        System.out.println(longAdder);
    }
}
