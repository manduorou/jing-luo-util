package com.jingluo.util.bean.tree;

import sun.reflect.generics.tree.Tree;

import java.util.Collection;

/**
 * 抽象类的实现类
 * @ClassName AbstractTreeFactory
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/27
 * @Version 1.0
 */
public abstract class AbstractTreeDataFactory<E> implements TreeDataFactory<E> {


    //TODO 构建树形数据集合
    @Override
    public Collection<E> buildTreeData(Collection<E> resources) {
        return null;
    }

    //TODO 设置迭代深度
    @Override
    public void setDeep() {

    }

    //TODO 转换为tree树形结构
    @Override
    public <I> Tree parseTree(Collection<? super TreeData<I, E>> resources) {

        return null;
    }
}

