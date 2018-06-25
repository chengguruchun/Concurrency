package com.cheng.concurrency.example.lock;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:10 2018/6/22
 * @Reference:
 */
@Slf4j
public class LockEx3 {

    private final Map<String, String> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public String get(String key) {
        readLock.lock();
        try {
           return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    //读多写少的情况下，不要用，会造成饥饿，比如一直在read，那么write 会一直等待~~~~
    public Set<String> getAllKeys() {
        writeLock.lock();
        try {

            return map.keySet();
        }finally {
            writeLock.unlock();
        }
    }

    class Data {

    }
}