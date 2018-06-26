package com.cheng.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author :cheng
 * @Description:  每次只开二个线程~~~~
 * @Date: created in 14:59 2018/6/25
 * @Reference:
 */
@Slf4j
public class ThreadPoolEx2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }
                    log.info("{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}