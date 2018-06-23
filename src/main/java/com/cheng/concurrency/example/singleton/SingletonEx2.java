package singleton;

import com.cheng.concurrency.annoations.NotRecommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author :cheng
 * @Description: 饿汉模式--- 线程安全
 * @Date: created in 21:51 2018/6/22
 * @Reference:
 */
@Slf4j
@NotRecommend
public class SingletonEx2 {
    // 私有构造函数
    private SingletonEx2() {

    }

    //单例对象
    private static SingletonEx2 instance = new SingletonEx2();

    //静态的工厂方法
    public static SingletonEx2 getInstance() {
        return instance;
    }

}