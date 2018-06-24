package com.cheng.concurrency.example.concurrent;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author :cheng
 * @Description:  线程安全，是treemap 的并发安全版本
 * @Date: created in 20:23 2018/6/24
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapEx1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                //最多200条线程运行同时
                try {
                    semaphore.acquire();
                    update(count);
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
        log.info("{}", map.size());
        executorService.shutdown();
    }

    public  static void update(final int i) {
        map.put(i, i);
    }

}