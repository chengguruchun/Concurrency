package atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author :cheng
 * @Description: ABA问题
 * @Date: created in 20:09 2018/6/22
 * @Reference: https://www.cnblogs.com/princessd8251/articles/5187403.html
 */
public class AtomicEx5 {
    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread in = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });

        Thread in1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println(c3);
            }
        });

        in.start();
        in1.start();
        in.join();
        in1.join();
        //加时间戳的

        Thread ou = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
                boolean stamp = reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
                reference.compareAndSet(101, 100, reference.getStamp(), reference.getStamp() + 1);
            }
        });

        Thread ou1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = reference.getStamp();
                try {

                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {

                }
                //同时比较  原始值和stamp时间戳~~
                boolean f3 = reference.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(f3);
            }
        });
        ou.start();
        ou1.start();
        ou.join();
        ou1.join();
    }
}