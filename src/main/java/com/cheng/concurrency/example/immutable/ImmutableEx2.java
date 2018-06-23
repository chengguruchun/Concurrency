package com.cheng.concurrency.example.immutable;

import com.cheng.concurrency.annoations.NotThreadSafe;
import com.cheng.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @Author :cheng
 * @Description: 不可变对象   :
 *
 * Collections.unmodifiab......
 *
 * guava 里面的immutable.....
 *
 * @Date: created in 20:03 2018/6/23
 * @Reference:
 */
@Slf4j
@ThreadSafe
public class ImmutableEx2 {

    private  static Map<Integer, Integer> map = Maps.newHashMap();


    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //基本类型和包装类，不可修改了
        //a = 23;
        //b = "3";

        //地址指向不可变了,里面的值可以修改的，所以不是不可变对象
        //map = Maps.newHashMap();

        map.put(1, 3);
        log.info("{}", map.get(1));

    }

}