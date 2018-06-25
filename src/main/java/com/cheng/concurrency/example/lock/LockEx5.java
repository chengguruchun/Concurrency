package com.cheng.concurrency.example.lock;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author :cheng
 * @Description:  StampedLock 测试~~
 * @Date: created in 21:10 2018/6/22
 * @Reference:
 */

/**
 *
 * 有关StampedLock的实现思想
 StampedLock的内部实现是基于CLH锁的,CLH锁是一种自旋锁,它保证没有饥饿的发生,并且可以保证FIFO(先进先出)的服务顺序.
 CLH锁的基本思想如下:锁维护一个等待线程队列,所有申请锁,但是没有成功的线程都记录在这个队列中,每一个节点代表一个线程,保存
 一个标记位(locked).用与判断当前线程是否已经释放锁;locked=true 没有获取到锁,false 已经成功释放了锁
 当一个线程视试图获得锁时,取得等待队列的尾部节点作为其前序节点.并使用类似如下代码判断前序节点是否已经成功释放锁:

 while (pred.locked) {

 }
 只要前序节点(pred)没有释放锁,则表示当前线程还不能继续执行,因此会自旋等待,
 反之,如果前序线程已经释放锁,则当前线程可以继续执行.
 释放锁时,也遵循这个逻辑,线程会将自身节点的locked位置标记位false,那么后续等待的线程就能继续执行了
 */
@Slf4j
@ThreadSafe
public class LockEx5 {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;
    public static  int count;
    private static  StampedLock LOCK = new StampedLock();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                //最多200条线程运行同时
                try {
                    semaphore.acquire();
                    add();
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
        log.info("count:{}", count);
    }

    //用基于aqs的lock加锁，而不是synchronize关键字，各有好处吧~~
    public  static void add() {
        long stamp = LOCK.writeLock();
        try {
            count++;
        }finally {
            LOCK.unlock(stamp);
        }
    }

}