package singleton;

import com.cheng.concurrency.annoations.NotRecommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author :cheng
 * @Description: 懒汉模式  --- 加了synchronized，所以线程安全，否则线程不安全
 * @Date: created in 21:44 2018/6/22
 * @Reference:
 */
@Slf4j
@NotRecommend
public class SingletonEx1 {
    private SingletonEx1(){

    }

    private static SingletonEx1 instance = null;

    public static synchronized SingletonEx1 getInstance() {
        if (instance == null) {
            instance = new SingletonEx1();
        }

        return instance;
    }
}