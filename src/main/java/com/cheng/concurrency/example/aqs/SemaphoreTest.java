package com.cheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
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
public class SemaphoreTest {
    private static final int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                        try {
                            semaphore.acquire();
                            test(threadNum);
                            semaphore.release();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
            );
        }
        exec.shutdown();//不再接受新的任务，可以继续处理保存在阻塞队列里面的
        //exec.shutdownNow();//不再接受新的任务，不处理保存在阻塞队列里面的，也会放弃正正在处理的线程
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}