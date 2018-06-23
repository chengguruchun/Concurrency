package com.cheng.concurrency.example.threadLocal;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:28 2018/6/23
 * @Reference: https://www.cnblogs.com/dolphin0520/p/3920407.html
 */
public class ThreadLocalTest {
    ThreadLocal<Long> lonhLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>() ;

    public void set() {
        lonhLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }


    public long getLong() {
        return lonhLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();
        test.set();

        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test.set();
                System.out.println(test.getString());
                System.out.println(test.getLong());
            }
        });

        thread.start();
        thread.join();
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}