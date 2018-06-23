package com.cheng.concurrency.example.atomic;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author :cheng
 * @Description: 使用AtomicInteger来保证count++原子性，达到多线程安全的目的
 * @Date: created in 17:30 2018/6/22
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class AtomicEx1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    //public static AtomicInteger count = new AtomicInteger(0);
    public static int count;
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

    public synchronized static void add() {
        //count.incrementAndGet();
        count++;
    }
}