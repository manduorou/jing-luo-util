package com.jingluo.util.ot.bean.tree;

import sun.reflect.generics.tree.Tree;

import java.util.Collection;

/**
 * 树形数据结构工厂
 *
 * @ClassName TreeFactory
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public interface TreeDataFactory<E> {
    int default_two_deep = 2; //默认2层
    int infinite_deep = 1; //无限递归
    int tree_deep = 3; //3层树
    Collection<E> buildTreeData(Collection<E> resources);

    void setDeep();

    <I> Tree parseTree(Collection<? super TreeData<I, E>> resources);

    static <E,I,T> Collection<E> turnToTree(Collection<? super TreeData<I,T>> resources){

        return null;
    }
}
