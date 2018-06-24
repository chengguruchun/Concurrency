package com.cheng.concurrency.example.concurrent;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author :cheng
 * @Description:  这些是并发容器 ------   区别与同步容器
 *                           是treeset的并发容器
 * @Date: created in 20:23 2018/6/24
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetEx1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

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
        log.info("{}", set.size());
        executorService.shutdown();
    }

    public  static void update(final int i) {
        set.add(i);
    }
}