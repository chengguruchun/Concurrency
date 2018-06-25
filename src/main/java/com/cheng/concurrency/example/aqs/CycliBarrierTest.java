package com.cheng.concurrency.example.aqs;

/**
 * @Author :cheng
 * @Description:  分组计算求和~~
 * @Date: created in 22:24 2018/6/24
 * @Reference:
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CycliBarrier：多个线程之间相互等待，处理更复杂的业务场景
 *
 *
 *
 * countDownLatch:一个
 */
@Slf4j
public class CycliBarrierTest {

    private static CyclicBarrier barrier = new CyclicBarrier(5);
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(() ->
            {
                try {
                    race(threadNum);
                }catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }

    }

    private static void race(int t) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", t);
        barrier.await();
        log.info("{} contiue", t);
    }
}