package com.example.thread;

/**
 * @Package: com.example.thread
 * @Description: 局部线程变量池，每个线程使用自己的，所以各个线程之间不共享
 * @author: liuxin
 * @date: 17/3/31 下午1:58
 */
public class SequenceB implements Sequence {

    /**
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     * 从线程的角度看，目标变量就象是线程的本地变量，这也是类名中“Local”所要表达的意思。
     */
    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }
}
