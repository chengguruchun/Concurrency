package com.cheng.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :cheng
 * @Description:  condition.await() 之后把执行的线程放在condition维护的队列中，  当收到signal之后，会吧condition里面的
 * 线程唤醒加入到  lock维护的队列中，然后竞争获取lock~~~
 * @Date: created in 11:30 2018/6/25
 * @Reference:
 */
@Slf4j
public class LockEx6 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        //jdk1.7以前
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        new Thread(() ->{
            try {
                lock.lock();
                log.info("wait dignal");//1 第一步
                condition.await();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal");//4 第四步
            lock.unlock();
        }, "t2").start();


        new Thread(() ->{
            lock.lock();
            log.info("get lock");//2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal");//3
            lock.unlock();
        }, "thread1").start();
    }
}