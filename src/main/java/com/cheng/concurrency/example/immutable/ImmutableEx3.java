package com.cheng.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author :cheng
 * @Description: guava 里面immutable...一些类的处理~~~
 *
 * @Date: created in 20:17 2018/6/23
 * @Reference:
 */
@Slf4j
public class ImmutableEx3 {
    private static final ImmutableList LIST = ImmutableList.of(1, 2, 3, 4);
    private static final ImmutableSet SET = ImmutableSet.copyOf(LIST);
    //奇数是key，偶数是value；
    private final static ImmutableMap<Integer, Integer> MAP1 = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> MAP2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();
    public static void main(String[] args) {
        //LIST.add(90);
        //SET.add(23);
        //MAP1.put(33, 55);
        System.out.println(MAP1.get(33));
    }
}