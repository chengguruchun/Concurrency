package com.cheng.concurrency.example.singleton;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 22:01 2018/6/22
 * @Reference:
 */
public class SingletonEx3 {
    private SingletonEx3() {

    }

    /**
     * 1、 memory = allocate() 分配内存空间
     * 2、 ctorInstance（） 初始化对象
     * 3、 Instance = memory 设置instance 指向刚分配的地址
     */

    //jvm 优化，发生了指令重排
    /**
     * 2.1、 memory = allocate() 分配内存空间
     * 2.3、 Instance = memory 设置instance 指向刚分配的地址
     * 2.2、 ctorInstance（） 初始化对象
     *
     */

    //线程不安全
    //private static SingletonEx3 instance = null;
    //线程安全
    private static volatile SingletonEx3 instance = null;
    public static SingletonEx3 getInstance() {
        if (instance == null) {//双重检查机制
            synchronized (SingletonEx3.class) { //B
                if (instance == null) {
                    instance = new SingletonEx3();//A 3(线程A 执行到第2.3, 初始化对象，这时B 线程执行到第34行，发现不为null，则返回instance，其实这时候还没有赋值)
                }
            }
        }

        return instance;
    }
}