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
public class FutureEx {
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do somethng in callable");
            Thread.sleep(5000);
            return "done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(new MyCallable());

        log.info("do something else");
        Thread.sleep(1000);

        String result = future.get();//会一直阻塞

        log.info("over - end");
        executorService.shutdown();
    }
}