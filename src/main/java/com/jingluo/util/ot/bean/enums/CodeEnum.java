package com.jingluo.util.ot.bean.enums;

/**
 * 详细介绍接口.
 *
 * @ClassName CodeEnum
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public interface CodeEnum<C,M> {
    abstract C getCode();
    abstract M getMsg();

    CodeEnum<C,M> successEnum();
    CodeEnum<C,M> errorEnum();
}
