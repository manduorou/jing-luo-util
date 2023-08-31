package com.jingluo.util.ot.json;

/**
 * 详细介绍接口.
 *
 * @ClassName JsonUtils
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public interface JLJson {
    static <T> T toObj(String json,Class<?> resourceCls){
        return null;
    }
    static JsonBean toBean(String json){
        return JsonUtils.toBean(json);
    }
    static <T> String parseObjToJson(T data){
        return JsonUtils.toJson(data);
    }
    static String formatJson(String json){
        return JsonUtils.format(json);
    }
}
