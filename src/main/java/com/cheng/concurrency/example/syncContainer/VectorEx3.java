package com.cheng.concurrency.example.syncContainer;

import com.cheng.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:11 2018/6/24
 * @Reference:
 */
@Slf4j
@NotThreadSafe
public class VectorEx3 {

    private static void test1(Vector<Integer> v1) {
        for (Integer integer : v1) {
            if(integer.equals(3)) {
                v1.remove(integer);
            }
        }
    }

    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> ite = v1.iterator();
        while (ite.hasNext()) {
            Integer i = ite.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }
    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        //test1(vector);    //java.util.ConcurrentModificationException
        test2(vector);      //java.util.ConcurrentModificationException
        //test3(vector);   //success

    }

}