package com.cheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:57 2018/6/24
 * @Reference:
 */
@Slf4j
public class CountDownLatchTest2 {
    private static final int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            //Thread.sleep(1);
            exec.execute(() -> {
                        try {
                            test(threadNum);
                        } catch (Exception e) {
                            e.getMessage();
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
            );
        }
        countDownLatch.await(10, TimeUnit.MICROSECONDS);//超过等待时间继续执行了，就不继续执行了~~~~~
        log.info("finished");
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1);
        log.info("{}", threadNum);
    }
}