package com.cheng.concurrency.example.aqs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :cheng
 * @Description: 独占锁
 * @Date: created in 9:32 2018/7/2
 * @Reference:
 */
public class Mutex implements Lock{
    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        protected boolean isHeldExclusively () {
            return getState() == 1;
        }

        //当状态为0时的时候获取锁
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        protected boolean tryRelease(int release) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();

            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCodition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCodition();
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.mappingCount();
    }
}