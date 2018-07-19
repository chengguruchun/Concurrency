package com.cheng.concurrency.example.blockqueue;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 12:00 2018/7/19
 * @Reference: https://my.oschina.net/sharkbobo/blog/270347
 */
import java.util.concurrent.Semaphore;

public class BlockingQvsSemaphore {

    /**
     * @Title: 使用Semaphore实现阻塞队列
     * @Description: 使用只有1个许可的
     * @param @param args    设定文件
     * @return void    返回类型
     * @throws
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Thread(new Producer()).start();
        new Thread(new BConsumer()).start();
        new Thread(new BConsumer()).start();
    }

}
/**
 * 信号类
 * */
class Signs{
    static Semaphore empty=new Semaphore(10); //信号量：记录仓库空的位置
    static Semaphore full=new Semaphore(0);   //信号量：记录仓库满的位置
    static Semaphore mutex=new Semaphore(1);  //临界区互斥访问信号量(二进制信号量)，相当于互斥锁。
}
/**
 * 生产者
 * */
class Producer implements Runnable{
    @SuppressWarnings("static-access")
    public void run() {
        try {
            while (true) {
                Signs.empty.acquire(); // 递减仓库空信号量，将消费计数器减1
                Signs.mutex.acquire(); // 进入临界区
                System.out.println("生成一个产品放入仓库");
                Signs.mutex.release(); // 离开临界区
                Signs.full.release(); // 递增仓库满信号量，将库存计数器加1
                Thread.currentThread().sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 消费者
 * */
class BConsumer implements Runnable{
    @SuppressWarnings("static-access")
    public void run() {
        try {
            while (true) {
                Signs.full.acquire(); // 递减仓库满信号量，将库存计数器减1
                Signs.mutex.acquire();
                System.out.println("从仓库拿出一个产品消费");
                Signs.mutex.release();
                Signs.empty.release(); // 递增仓库空信号量，将消费计数器加1
                Thread.currentThread().sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}