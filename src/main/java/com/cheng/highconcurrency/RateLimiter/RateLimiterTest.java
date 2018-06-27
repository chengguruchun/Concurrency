package com.cheng.highconcurrency.RateLimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 19:42 2018/6/26
 * @Reference:
 */
public class RateLimiterTest {

    /**
     * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
     * acquire(N)可以获取多个令牌。
     */
    public static void main(String[] args) {
        RateLimiter r = RateLimiter.create(3);
        while (true) {
            System.out.println(r.acquire(1));
        }
    }
}