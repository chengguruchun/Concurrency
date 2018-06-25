package com.cheng.concurrency.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 10:32 2018/6/25
 * @Reference: http://www.dewen.net.cn/q/9077
 *
 * 如何设置多线程的断电（在idea里面~~~）
 * https://www.cnblogs.com/woshimrf/p/5843004.html
 */
public class LockEx1 {

    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();//在阻塞的时候可以响应interrupt().然后处理InterruptedException，否则拿不到锁一直阻塞啊
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "interruped!!");
                }
            }
        }, "lcc");
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000);
        System.out.println("over");
    }

    public static void main2(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "interruped!!");
            }
        }, "lcc");
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000);
        System.out.println("over");
    }

    public static void main3(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
        });
        t1.start();
        t1.interrupt();
        Thread.sleep(1000);
    }
}