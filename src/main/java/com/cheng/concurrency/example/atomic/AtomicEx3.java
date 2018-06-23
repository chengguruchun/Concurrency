package com.cheng.concurrency.example.atomic;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 19:55 2018/6/22
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class AtomicEx3 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);//2
        count.compareAndSet(1, 4);//no
        count.compareAndSet(2, 90);//90
        count.compareAndSet(4, 89);//no
        count.compareAndSet(90, 34);//34
        count.compareAndSet(90, 78);//no
        log.info("count:{}", count.get());
    }

}