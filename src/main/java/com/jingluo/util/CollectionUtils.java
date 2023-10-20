package com.jingluo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 集合对象工具
 * @ClassName CollectionUtils
 * @Author oldTree
 * @Date 2023/8/10
 * @Version 1.0
 */
public class CollectionUtils {
    /**
     * @param collection 被切割的集合
     * @param size 每个集合的最大数量
     * @return 切割后的数据
     * @param <E> 范型集合类型
     */
    public static <E> List<List<E>> cut(Collection<E> collection,int size){
        // 一个集合最多数量
        BigDecimal singleListCount = new BigDecimal(size);
        // 当前集合总数量
        BigDecimal nowAllCount = new BigDecimal(collection.size());
        // 分割集合数量 = 总数量 / 当个集合最多数量， 值始终要五入，例如 当个集合最多100，总数量是120个，那么需要两个集合
        int listCount = nowAllCount.divide(singleListCount, 0, RoundingMode.UP).intValue();
        // 创建 分割集合
        List<List<E>> partitionList = new LinkedList<>();
        for (int i = 0; i < listCount; i++) {
            partitionList.add(new LinkedList<>());
        }
        // 计算 一个集合平均数量，避免出现 两个集合，一个集合有150个，另外一个集合只有1个的现象
        int avg = collection.size() / listCount;
        // 将数据平均放入 分割好的集合中
        for (E s : collection) {
            // 遍历放入分割好的集合
            for (int i = 0; i < listCount; i++) {
                if (partitionList.get(i).size() < avg) {
                    partitionList.get(i).add(s);
                    break;
                }
            }
        }
        return partitionList;
    }
    public static <E> Collection<E> buildEmptyList(){
        return new ArrayList<>();
    }
    public static <K,V> Map<K,V> buildEmptyMap(){
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

