package com.cheng.concurrency.example.threadLocal;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:10 2018/6/23
 * @Reference: http://www.cnblogs.com/sean-zou/archive/2013/04/22/3710070.html
 *
 * https://www.cnblogs.com/dolphin0520/p/3920407.html
 */
public class Task implements Runnable{
    private static ThreadLocal<Double> value = new ThreadLocal(){
        @Override
        protected Double initialValue() {

            return Math.random();
        }
    };

    @Override
    public void run() {
        System.out.println( value.get());

    }

    public static void main(String[] args) {
        Task t = new Task();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }
}