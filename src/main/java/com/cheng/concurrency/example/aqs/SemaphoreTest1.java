package com.cheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author :cheng
 * @Description:  控制并发线程的最大量~~   --->  acquire()  and  release()
 * @Date: created in 21:57 2018/6/24
 * @Reference:
 */
@Slf4j
public class SemaphoreTest1 {
    private static final int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                        try {
                            semaphore.acquire(3);//这里相当于单线程了
                            test(threadNum);
                            semaphore.release(3);
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
            );
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}