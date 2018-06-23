package com.cheng.concurrency.example.atomic;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 19:59 2018/6/22
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class AtomicEx4 {
    //修改的字段必须是volatile 修饰，而且不能是static修饰~~
    private static AtomicIntegerFieldUpdater<AtomicEx4> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicEx4.class, "count");

    @Getter
    public volatile int count = 100;

    private static AtomicEx4 ex4 = new AtomicEx4();

    public static void main(String[] args) {
        if (updater.compareAndSet(ex4, 100, 1200)) {
            log.info("update success 1, {}", ex4.getCount());
        }
        if (updater.compareAndSet(ex4, 100, 34)) {
            log.info("update success 2, {}", ex4.getCount());
        }else {
            log.info("update failed 2,{}", ex4.getCount());
        }

    }
}