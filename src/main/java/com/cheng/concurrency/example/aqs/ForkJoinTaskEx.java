package com.cheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 13:41 2018/6/25
 * @Reference:
 */
@Slf4j
public class ForkJoinTaskEx extends RecursiveTask<Integer>{

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskEx(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            //// 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskEx left = new ForkJoinTaskEx(start, middle);
            ForkJoinTaskEx right = new ForkJoinTaskEx(middle + 1, end);
            left.fork();
            right.fork();

            // 等待任务执行结束合并其结果
            int leftResult = left.join();
            int rightResult = right.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTaskEx task = new ForkJoinTaskEx(1 ,100);

        Future<Integer> future = pool.submit(task);

        try {
            log.info("result:{}", future.get());
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
    }
}