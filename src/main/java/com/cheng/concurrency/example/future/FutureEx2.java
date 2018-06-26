package com.cheng.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 13:15 2018/6/25
 * @Reference:
 */
@Slf4j
public class FutureEx2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do somethng in callable");
                Thread.sleep(5000);
                return "done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);

        String result = futureTask.get();
        log.info("{}", result);
    }
}