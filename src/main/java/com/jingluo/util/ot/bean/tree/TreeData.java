package com.jingluo.util.ot.bean.tree;


/**
 * 属性数据接口
 * @ClassName TreeData
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/27
 * @Version 1.0
 */
public interface TreeData<I,T>{
    void setChild(T data);
    I parentId();
}
