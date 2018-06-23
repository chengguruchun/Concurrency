package com.cheng.concurrency.example.immutable;

import com.cheng.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author :cheng
 * @Description: 不可变对象
 * @Date: created in 20:03 2018/6/23
 * @Reference:
 */
@Slf4j
@NotThreadSafe
public class ImmutableEx1 {
    private final static Integer a = 1;
    private static final String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();


    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
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

    private void test(final int a) {
        //如果传过来的是final（形参），那么在方法里面的值也是不可变的，比如下面这个就是不可修改的
        //a = 1;
    }
}