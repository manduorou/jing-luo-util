package com.jingluo.util.ot.bean.enums;

/**
 * 详细介绍接口.
 *
 * @ClassName HttpCodeEnum
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public enum HttpCodeEnum implements CodeEnum<String, String>{
    SERVICE_SUCCESS("1111","业务执行成功！"),
    SERVICE_ERROR("0000","业务执行失败！");
    private final String code;
    private final String msg;
    HttpCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public CodeEnum<String, String> successCodeEnum() {
        return SERVICE_SUCCESS;
    }

    @Override
    public CodeEnum<String, String> errorCodeEnum() {
        return SERVICE_ERROR;
    }
}
