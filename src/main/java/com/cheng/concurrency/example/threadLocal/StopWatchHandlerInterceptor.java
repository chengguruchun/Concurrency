package com.cheng.concurrency.example.threadLocal;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 15:24 2018/7/15
 * @Reference:
 */
public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter{
    private NamedThreadLocal<Long> startThreadLocal = new NamedThreadLocal<>("stopWatchStartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        long beginTime = System.currentTimeMillis();
        startThreadLocal.set(beginTime);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = startThreadLocal.get()
;
        long consumeTime = endTime - startTime;
        if(consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
            //TODO 记录到日志文件
            System.out.println(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
        }
    }
}