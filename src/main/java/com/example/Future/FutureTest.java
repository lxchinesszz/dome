package com.example.Future;

import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;

/**
 * @Package: com.example.Future
 * @Description: 使用JDK8 处理异步高并发
 * @author: liuxin
 * @date: 17/4/11 上午9:40
 */
public class FutureTest {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        new Thread(new AskThead(future)).start();
        Thread.sleep(10000);
        System.out.println(future.complete(5));
//        future.thenAcceptAsync()

    }
}

class AskThead implements Runnable {
    CompletableFuture<Integer> future = null;

    AskThead(CompletableFuture<Integer> future) {
        this.future = future;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            num = future.get();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}