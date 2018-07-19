package com.cheng.concurrency.example.blockqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 11:37 2018/7/19
 * @Reference: https://www.cnblogs.com/duanxz/p/3252267.html
 */
public class SynchronousQueueEx {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
                try {
                    queue.put(1) ;
                } catch (InterruptedException e) {

                }
                System.out.println("put thread end");
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    System.out.println("take from putThread:" + queue.take());
                } catch (InterruptedException e) {

                }

                System.out.println("take thread end");
            }
        });
        //阻塞直到queue里面有数据
        takeThread.start();
        Thread.sleep(1000);
        putThread.start();

    }
}