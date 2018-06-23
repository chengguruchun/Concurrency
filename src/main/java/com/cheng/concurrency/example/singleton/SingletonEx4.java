package com.cheng.concurrency.example.singleton;

import com.cheng.concurrency.annoations.Recommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author :cheng
 * @Description:  最安全的单例模式
 * @Date: created in 22:17 2018/6/22
 * @Reference:
 */
@Slf4j
@Recommend
public class SingletonEx4 {
    private SingletonEx4(){

    }

    public static SingletonEx4 getInstance() {
       return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonEx4 instance;

        Singleton() {
            instance = new SingletonEx4();
        }

        public SingletonEx4 getInstance() {
            return instance;
        }
    }
}