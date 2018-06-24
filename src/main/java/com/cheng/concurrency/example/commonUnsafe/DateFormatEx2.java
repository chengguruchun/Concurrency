package com.cheng.concurrency.example.commonUnsafe;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author :cheng
 * @Description:  变为局部变量，那么变为线程安全
 * @Date: created in 22:11 2018/6/23
 * @Reference:
 */

@Slf4j
@ThreadSafe
public class DateFormatEx2 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                //最多200条线程运行同时
                try {
                    semaphore.acquire();
                    update();
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
        executorService.shutdown();
    }

    public  static void update() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

            simpleDateFormat.parse("20100204");
        } catch (ParseException e) {

        }
    }
}