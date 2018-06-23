package com.cheng.concurrency.example.atomic;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author :cheng
 * @Description: 使用AtomicInteger来保证count++原子性，达到多线程安全的目的
 * @Date: created in 17:30 2018/6/22
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class AtomicEx2 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;
    //两个区别:https://www.cnblogs.com/ten951/p/6590596.html
    /**
     * 大家对AtomicInteger的基本实现机制应该比较了解,它们是在一个死循环内,不断尝试修改目标值,知道修改成功,如果竞争不激烈,
     * 那么修改成功的概率就很高,否则,修改失败的概率就很高,在大量修改失败时,这些原子操作就会进行多次循环尝试,因此
     * 性能就会受到影响那么竞争激烈的时候,
     *
     * 我们应该如何进一步提高系统性能呢?一种基本方案就是可以使用热点分离,将
     * 竞争的数据进行分解.基于这个思路,大家应该可以想到一种对传统AtomicInteger等原子类的改进方法,虽然在CAS
     * 操作中没有锁,但是像减少锁粒度这种分离热点的思路依然可以使用,一种可行的方案就是仿造ConcurrengHashMap,
     * 将热点数据分离,比如,可以将AtomicInteger的内部核心数据value分离成一个数组,每个线程访问时,通过哈希等
     * 算法映射到其中一个数字进行计数,而最终的计数结果,则为这个数组的求和累加,其中,热点数据value被分离成多个
     * 单元cell,每个cell独自维护内部的值,当前对象的实际值由所有的cell累计合成,这样,热点就进行了有效的分离,
     * 提高了并行度,LongAdder正是使用了这种思想.
     */
    public static AtomicLong count1 = new AtomicLong(0);
    public static LongAdder count = new LongAdder();

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

    public static void add() {
        count.increment();
    }
}