package com.cheng.concurrency.example.syncContainer;

import com.cheng.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:23 2018/6/24
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class CollectionsSetEx1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static Set<Integer> set = Collections.synchronizedSet(Sets.newHashSet());

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