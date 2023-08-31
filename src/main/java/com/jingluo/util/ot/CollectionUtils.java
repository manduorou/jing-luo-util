package com.jingluo.util.ot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合对象工具
 * @ClassName CollectionUtils
 * @Author oldTree
 * @Date 2023/8/10
 * @Version 1.0
 */
public class CollectionUtils {
    /**
     * TODO 实现集合切割
     * @param collection 被切割的集合
     * @param num 切割的数量
     * @return 切割后的多个集合
     * @param <E> 范型集合类型
     */
    public static <E> Collection<Collection<E>> cut(Collection<E> collection,int num){
        return null;
    }
    public static <E> Collection<E> emptyList(){
        return new ArrayList<>();
    }
    public static <K,V> Map<K,V> emptyMap(){
        return new HashMap<>();
    }
    public static <E> boolean isEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <K,V> boolean isEmpty(Map<K,V> map) {
        return map == null || map.isEmpty();
    }
    public static <E> boolean nonEmpty(Collection<E> fields) {
        return !isEmpty(fields);
    }
    public static <K,V> boolean nonEmpty(Map<K,V> map) {
        return !isEmpty(map);
    }
    public static <E> boolean isArrayEmpty(E[] es) {
        return es == null || es.length == 0;
    }
}

