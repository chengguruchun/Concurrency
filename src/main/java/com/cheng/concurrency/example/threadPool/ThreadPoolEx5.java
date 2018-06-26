package com.cheng.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author :cheng
 * @Description:  按计划执行线程~
 * @Date: created in 14:59 2018/6/25
 * @Reference:
 */
@Slf4j
public class ThreadPoolEx5 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        /*final int index = 90;
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("{}", index);
            }
        }, 3, TimeUnit.SECONDS);*/


        /**
         * 每三秒执行一次~~
         *
         15:26:49.270 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:26:52.271 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:26:55.269 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:26:58.269 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:27:01.270 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:27:04.270 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:27:07.270 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll
         15:27:10.270 [pool-1-thread-1] WARN com.cheng.concurrency.example.threadPool.ThreadPoolEx5 - ollll

         */
        /*executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("ollll");
            }
        }, 1 ,3, TimeUnit.SECONDS);*/
        //executorService.shutdown();
        //executorService.shutdownNow();

        //定时器的作用~~~   每5秒执行动作~
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("okkkk");
            }
        }, new Date(), 5 * 1000);

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("niui");
            }
        }, new Date(), 4 * 1000);
    }
}