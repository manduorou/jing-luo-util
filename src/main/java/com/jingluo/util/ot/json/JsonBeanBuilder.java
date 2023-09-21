package com.jingluo.util.ot.json;

/**
 * JsonBean类型对象构建者
 * @ClassName JsonBeanBuilder
 * @Author oldTree
 * @Date 2023/8/31
 * @Version 1.0
 */
public interface JsonBeanBuilder {
    static JsonBean build(String json){
        return new JsonBean(json);
    }
    static JsonBean[] builtArray(String json){
        return null;
    }
}
