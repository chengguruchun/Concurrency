package com.cheng.concurrency.example.commonUnsafe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:05 2018/6/28
 * @Reference:
 */
public class ConcurrentDateUtil {
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date parse(String date) throws ParseException {
        return threadLocal.get().parse(date);
    }

    public static String format(Date date) {

        return threadLocal.get().format(date);
    }

}