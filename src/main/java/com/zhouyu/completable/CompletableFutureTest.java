package com.zhouyu.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> hello1 = CompletableFuture.runAsync(() -> System.out.println("hello"));
        CompletableFuture<Void> hello2 = CompletableFuture.runAsync(() -> System.out.println("hello2"));
        CompletableFuture.allOf(hello1, hello2).thenRun(() -> System.out.println("all completed"));
    }
}