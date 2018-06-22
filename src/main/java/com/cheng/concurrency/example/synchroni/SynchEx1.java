package synchroni;

import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:34 2018/6/22
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class SynchEx1 {

    /**
     * 修饰一个代码块
     */
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}- {}", j, i);
            }
        }
    }

    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} -{}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchEx1 ex1 = new SynchEx1();
        SynchEx1 ex2 = new SynchEx1();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() ->
           ex2.test2(1)
        );

        service.execute(() ->
            ex1.test2(2)
        );

        service.shutdown();
    }

}