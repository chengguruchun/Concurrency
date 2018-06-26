package com.cheng.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 14:59 2018/6/25
 * @Reference:
 */
@Slf4j
public class ThreadPoolEx {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}