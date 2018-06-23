package singleton;

import com.cheng.concurrency.annoations.ThreadSafe;

/**
 * @Author :cheng
 * @Description: 饿汉模式
 * 在类加载时候进行创建
 * @Date: created in 22:22 2018/6/22
 * @Reference:
 */
@ThreadSafe
public class SingletonEx5 {
    private SingletonEx5() {

    }

    private static SingletonEx5 instance = null;
    static {
        instance = new SingletonEx5();
    }

    public static SingletonEx5 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonEx5.getInstance().hashCode());
        System.out.println(SingletonEx5.getInstance().hashCode());
    }

}