package com.cheng.concurrency.example.copyonwrite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 11:37 2018/7/15
 * @Reference: https://www.cnblogs.com/hapjin/p/4840107.html
 */

/**
 * CopyOnWrite容器适用于读多写少的场景。因为写操作时，需要复制一个容器，造成内存开销很大，也需要根据实际应用把握初始容器的大小。

 不适合于数据的强一致性场合。若要求数据修改之后立即能被读到，则不能用写时复制技术。因为它是最终一致性。

 总结：写时复制技术是一种很好的提高并发性的手段。
 * @param <K>
 * @param <V>
 */
public class CopyOnWriteMap<K, V> implements Map<K, V> ,Cloneable{

    private volatile  Map<K, V> internalMap;

    public CopyOnWriteMap() {
        internalMap = new HashMap<>(100);//初始大小应根据实际应用来指定
    }
    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
       return internalMap.containsValue(value);
    }

    @Override
    public V get(Object key) {

        return internalMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        //这个put的方法只能单线程执行了啊
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            V val = newMap.put(key, value);
            internalMap = newMap;
            //原先那个map应该会被垃圾回收，没有可达性的沿用了
            return val;
        }
    }

    @Override
    public V remove(Object key) {
        return internalMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);

            newMap.putAll(m);
            internalMap = newMap;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
       return internalMap.keySet();
    }

    @Override
    public Collection<V> values() {

       return  internalMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return internalMap.entrySet();
    }
}