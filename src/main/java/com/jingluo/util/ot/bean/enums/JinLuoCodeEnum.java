package com.jingluo.util.ot.bean.enums;

/**
 * 详细介绍类的情况.
 *
 * @ClassName JinLuoCodeEnum
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public enum JinLuoCodeEnum implements CodeEnum<String,String>{
    SUCCESS("-1111","异常情况"),
    ERROR("-1111","异常情况");
    private final String key;
    private final String msg;

    JinLuoCodeEnum(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return key;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

