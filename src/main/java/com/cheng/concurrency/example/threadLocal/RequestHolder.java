package com.cheng.concurrency.example.threadLocal;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:57 2018/6/23
 * @Reference:
 */
public class RequestHolder {
    private final static  ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}