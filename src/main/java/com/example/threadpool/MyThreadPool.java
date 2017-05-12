package com.example.threadpool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @Package: com.example.threadpool
 * @Description: 线程池测试, 每个线程有自己的线程变量
 * @author: liuxin
 * @date: 17/4/5 上午9:14
 */
public class MyThreadPool {

    private static final ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public static int getNum() {
        num.set(num.get() + 1);
        return num.get();
    }

    public static void main(String[] args) throws Exception {
        //TODO 固定线程数量


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task   run:"+System.currentTimeMillis());
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0,1000);
    }
}
