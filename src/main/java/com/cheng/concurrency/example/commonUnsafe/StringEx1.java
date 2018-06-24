package com.cheng.concurrency.example.commonUnsafe;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:48 2018/6/23
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class StringEx1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static StringBuffer count = new StringBuffer();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                //最多200条线程运行同时
                try {
                    semaphore.acquire();
                    update();
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
        log.info("count:{}", count.length());
    }

    public  static void update() {
        count.append("1");
    }
}