package com.jingluo.util.ot.bean.tree;

/**
 * 详细介绍接口.
 *
 * @ClassName SimpleTree
 * @Author oldTree
 * @Date 2023/8/27
 * @Version 1.0
 */
public interface SimpleTree extends TreeData<String,SimpleTree>{
    @Override
    void setChild(SimpleTree data);
}
