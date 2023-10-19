package com.jingluo.util.ot.bean;

import java.util.Map;

/**
 * bean生成工厂
 * @ClassName BeanFactory
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/27
 * @Version 1.0
 */
public interface BeanFactory<T> {
    T built();
    <K,V> T builtByMap(Map<K,V> properties);
    T builtByJson(String json);
}
