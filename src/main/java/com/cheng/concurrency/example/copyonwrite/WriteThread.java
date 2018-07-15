package com.cheng.concurrency.example.copyonwrite;

import java.util.List;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 11:54 2018/7/15
 * @Reference:
 */
public class WriteThread implements Runnable{
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }
    @Override
    public void run() {
        this.list.add(90);
    }
}