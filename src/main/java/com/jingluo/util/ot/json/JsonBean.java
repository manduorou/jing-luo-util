package com.jingluo.util.ot.json;

/**
 * json的bean对象
 *
 * @ClassName JsonBean
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public class JsonBean {
    private String json;
    private StringBuffer jsonContext;

    public JsonBean(String json) {
        this.json = json;
        this.jsonContext = new StringBuffer(json);
    }
    //获取int
    Integer getInt(String key){
        return Integer.valueOf(key);
    }
    JsonBean getJsonBean(String key) {
        return null;
    }
}

