package com.jingluo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Bundle
 * @Author 鲸落网络-oldTree
 * @Date 2023/9/29
 * @Version 1.0
 */
public class Bundle<K, V> extends ConcurrentHashMap<K, V> implements Map<K, V> {
    public Bundle(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public Bundle(int initialCapacity) {
        super(initialCapacity);
    }

    public Bundle() {
    }

    public Bundle(Map<? extends K, ? extends V> m) {
        super(m);
    }

    Bundle<K, V> push(K key, V value) {
        this.put(key, value);
        return this;
    }
}

