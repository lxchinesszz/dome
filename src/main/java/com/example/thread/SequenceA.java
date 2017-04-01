package com.example.thread;

/**
 * @Package: com.example.thread
 * @Description: 每个线程调用都会刷新值去覆盖，所以，所有线程共同消费number
 * @author: liuxin
 * @date: 17/3/31 下午1:51
 */
public class SequenceA implements Sequence {
    private static volatile  int number=0;
    @Override
    public int getNumber() {
        number=number+1;
        return number;
    }
}
