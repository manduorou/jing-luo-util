package com.jingluo.util.ot.bean.enums;

/**
 * 响应状态枚举
 * 拒绝硬编码
 * @ClassName HttpCodeEnum
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public enum HttpCodeEnum implements CodeEnum<String, String> {
    SERVICE_SUCCESS("1111", "业务执行成功"),
    SERVICE_ERROR("0000", "业务执行失败"),

    INSERT_SUCCESS("1111", "添加成功"),
    DELETE_SUCCESS("1111", "删除成功"),
    UPDATE_SUCCESS("1111", "更新成功"),
    SELECT_SUCCESS("1111", "查询成功"),
    HANDLER_SUCCESS("1111", "操作成功"),
    USER_CHANGE_PWD_SUCCESS("1111", "修改密码成功"),
    USER_LOGIN_SUCCESS("1111", "登陆成功"),

    LOGIN_AUTH("2008", "未登陆"),

    INSERT_ERROR("2001", "添加失败"),
    DELETE_ERROR("2001", "删除失败"),
    UPDATE_ERROR("2001", "更新失败"),
    SELECT_ERROR("2001", "查询失败"),

    NO_PERMISSION_ERROR("2003", "没有操作权限"),
    USER_LOGIN_ERROR("2004", "登陆失败"),
    USER_CHECK_OLD_PWD_ERROR("2005", "旧密码不一致"),
    USER_CHECK_AGA_PWD_ERROR("2005", "重复密码不一致"),
    USER_CHECK_NEW_PWD_ERROR("2006", "新密码与原密码一致，不必修改"),
    USER_PWD_ERROR("2007", "用户密码错误"),
    ARGUMENT_VALID_ERROR("2010", "参数校验异常"),
    USER_USERNAME_NOTFOUND_ERROR("2011", "用户不存在"),
    USER_STATUS_ERROR("2012", "用户被冻结,无法登陆"),
    USER_NOON_MENU_AUTH("2013", "用户还未分配菜单权限，导致无法登陆"),
    ACCOUNT_STOP("2014", "账号已注销"),

    ARGUMENT_ERROR("2301", "方法参数异常，系统异常信息"),
    ILLEGAL_REQUEST("2222", "非法请求"),
    REPEAT_SUBMIT("2223", "重复提交"),
    ;
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
    public CodeEnum<String, String> successEnum() {
        return SERVICE_SUCCESS;
    }

    @Override
    public CodeEnum<String, String> errorEnum() {
        return SERVICE_ERROR;
    }

}
