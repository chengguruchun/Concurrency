package com.cheng.concurrency.example.copyonwrite;

import java.util.List;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 11:51 2018/7/15
 * @Reference:
 */
public class ReadThread implements Runnable{
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;

    }

    @Override
    public void run() {
        for (Integer l : list) {
            System.out.println("readThread:" + l);
        }
    }
}