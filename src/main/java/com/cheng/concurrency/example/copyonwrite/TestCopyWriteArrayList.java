package com.cheng.concurrency.example.copyonwrite;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 11:55 2018/7/15
 * @Reference:
 */
public class TestCopyWriteArrayList {
    private void test() {
        List<Integer> tempList = Arrays.asList(new Integer[] {1, 2});
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);

        //2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));

        System.out.println("copyList size:"+copyList.size());
        executorService.shutdown();;
    }

    public static void main(String[] args) {
        new TestCopyWriteArrayList().test();
    }
}