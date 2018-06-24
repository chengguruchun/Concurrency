package com.cheng.concurrency.example.syncContainer;

import com.cheng.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:11 2018/6/24
 * @Reference:
 */
@Slf4j
@NotThreadSafe
public class VectorEx2 {


    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }

        Thread t = new Thread() {
            public void run() {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            }
        };

        Thread t2 = new Thread(){
            public void run() {
                for (int i = 0; i < vector.size()/**当执行到这步时，线程t执行，把
                 这个下标下的元素删除，则不能get了，为 数组越界*/; i++) {
                    vector.get(i);
                }
            }
        };

        t.start();
        t2.start();

    }
}