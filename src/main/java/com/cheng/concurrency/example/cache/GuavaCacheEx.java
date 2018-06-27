package com.cheng.concurrency.example.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 20:44 2018/6/26
 * @Reference:
 */
@Slf4j
public class GuavaCacheEx {
    public static void main(String[] args) {
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {

                        return -1;
                    }
                });

        log.info("{}", cache.getIfPresent("key1")); // null
        cache.put("key1", 1);
        log.info("{}", cache.getIfPresent("key1"));
        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1")); // null
        try {
            log.info("{}", cache.get("key2"));
            cache.put("key2", 333);
            log.info("{}", cache.get("key2"));
            for (int i = 3; i < 15; i++) {
                cache.put("key" + i, i);
            }
            log.info("{}", cache.size());
            log.info("{}", cache.getIfPresent("key2")); // null

            Thread.sleep(11000);
            log.info("{}", cache.getIfPresent("key5"));
            log.info("{}, {}", cache.stats().hitCount(), cache.stats().missCount());

            log.info("{}, {}", cache.stats().hitRate(), cache.stats().missRate());
        } catch (ExecutionException e) {

        } catch (InterruptedException e) {

        }
    }
}