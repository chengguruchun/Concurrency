package com.cheng.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 10:05 2018/6/25
 * @Reference: https://blog.csdn.net/tolcf/article/details/50925145
 */
public class CountDownCompCyclicBarrEx1 {

    //线程在countdown() 之后，会继续执行自己的任务，而cyclicBarric会在所有线程任务结束之后，才会继续后序任务
    //这个是区别之一

    /**
     *
     *
     id:2
     id:1
     线程组任务1结束，其他任务开始
     id:4
     线程组任务4结束，其他任务开始
     id:3
     线程组任务3结束，其他任务开始
     id:0
     线程组任务2结束，其他任务开始
     over!
     线程组任务0结束，其他任务开始

     */
    public static void main1(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("over!");
    }

    static class readNum implements Runnable{
        private int id;
        private CountDownLatch c;

        public readNum(int id, CountDownLatch c) {
            this.id = id;
            this.c = c;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id);
                c.countDown();
                System.out.println("线程组任务" + id + "结束，其他任务开始");
            }
        }
    }

    /**
     result:

     id:1
     id:2
     id:0
     id:4
     id:3
     线程组执行结束
     线程组任务3结束，其他任务继续！
     线程组任务1结束，其他任务继续！
     线程组任务2结束，其他任务继续！
     线程组任务0结束，其他任务继续！
     线程组任务4结束，其他任务继续！
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("线程组执行结束");
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new readNum1(i, cyclicBarrier)).start();
        }

        //cyclicBarrier可以重复利用，但是countdownlatch不可以~~~~
        /*for (int i = 11; i < 16; i++) {
            new Thread(new readNum1(i, cyclicBarrier)).start();
        }*/

    }

    static class readNum1 implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;

        public readNum1(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }


        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id);
                try {
                    cyclicBarrier.await();

                    System.out.println("线程组任务" + id + "结束，其他任务继续！");
                }catch (Exception r) {
                    r.getMessage();
                }
            }
        }
    }
}