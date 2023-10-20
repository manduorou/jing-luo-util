package com.jingluo.util.json;

/**
 * 详细介绍接口.
 *
 * @ClassName JsonUtils
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public interface JLJson {
    static <T> String toJson(T data){
        return JsonUtils.toJson(data);
    }
    static String formatJson(String json){
        return JsonUtils.format(json);
    }
}
