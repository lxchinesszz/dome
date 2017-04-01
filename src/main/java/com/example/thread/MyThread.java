package com.example.thread;

/**
 * @Package: com.example.thread
 * @Description: 多线程
 * @author: liuxin
 * @date: 17/3/23 下午4:35
 */
public class MyThread extends Thread {
    Sequence sequence;

    MyThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "<---->" + sequence.getNumber());
        }
    }

    public static void main(String[] args) {

        Sequence sequence = new SequenceA();
        MyThread t0 = new MyThread(sequence);
        MyThread t1 = new MyThread(sequence);
        MyThread t2 = new MyThread(sequence);
        t0.start();t0.setPriority(10);
        t1.start();t1.setPriority(9);
        t2.start();t2.setPriority(8);


        Sequence sequence1 = new SequenceB();
        MyThread t3 = new MyThread(sequence1);
        MyThread t4 = new MyThread(sequence1);
        MyThread t5 = new MyThread(sequence1);
        t3.start();t3.setPriority(7);
        t4.start();t4.setPriority(6);
        t5.start();t5.setPriority(5);


    }
}
