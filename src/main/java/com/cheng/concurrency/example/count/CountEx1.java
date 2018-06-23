package com.cheng.concurrency.example.count;

import com.cheng.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author :cheng
 * @Description:  volatile只保持可见性，不保持原子性~~~
 * @Date: created in 21:10 2018/6/22
 * @Reference:
 */
@Slf4j
@NotThreadSafe
public class CountEx1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static volatile int count;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                //最多200条线程运行同时
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    //e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        //等所有线程全部结束
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    public  static void add() {
        count++;
        //1. get count
        //2. +1
        //3. 返回 count
    }

}