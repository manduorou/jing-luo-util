package com.jingluo.util.ot.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * bean工具类
 * @ClassName BeanCopier
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public final class BeanCopier{

    public static<T> T convertOne(Object resource,Class<T> tagClass){
        T builtBean = builtBean(tagClass);
        resolveFields(resource, builtBean, tagClass);
        return builtBean;
    }

    /**
     * 拷贝集合中对象的属性
     * @param resources 集合对象
     * @param tagClass 拷贝生成的类对象
     * @return 返回拷贝后的集合对象
     * @param <T> 范型
     */
    public static<T,O> Collection<T> convertCollection(Collection<O> resources,Class<T> tagClass){
        Collection<T> collection = new ArrayList<>();
        if(resources == null || resources.isEmpty()){
            return Collections.emptyList();
        }
        resources.forEach(resource -> {
            collection.add(convertOne(resource,tagClass));
        });
        return collection;
    }

    /**
     * 处理资源对象和目标对象的所有属性，
     * 拷贝相同的属性的value
     * 这里考虑：
     * 1、两者属性相同数量
     * 2、资源对象属性数量多余目标属性数量
     * 3、目标属性数量多于资源属性数量
     * 其次在处理属性的使用，需要考虑属性
     * @param resource 资源对象
     * @param builtBean 目标对象
     * @param tagClass 目标对象类
     */
    private static <T> void resolveFields(Object resource, T builtBean, Class<T> tagClass) {
        Field[] fields = tagClass.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                handlerFieldValue(resource, builtBean, field);
                field.setAccessible(false);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * * 下面应该判断是否属性的类型都相同？相同直接复制，不相同类型属性转换为目标的属性类型
     * @param resource 资源数据
     * @param builtBean 目标数据
     * @param field 目标数据中的属性
     * @param <T> 返回对应类型
     * @throws IllegalAccessException 异常
     * @throws InvocationTargetException 异常
     * @throws NoSuchMethodException 异常
     * @throws InstantiationException 异常
     */
    private static <T> void handlerFieldValue(Object resource, T builtBean, Field field) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException  {
        String fieldName = field.getName();//获取要注入属性的属性名称
        //获取数据上的对应属性
        Field tagDeclaredField = null;
        try {
            tagDeclaredField = resource.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            //不作处理
            return;
        }
        tagDeclaredField.setAccessible(true);
        if(tagDeclaredField.getType().equals(field.getType())){
            Object value = tagDeclaredField.get(resource);
            field.set(builtBean,value);
            return;
        }
        handlerFieldByDifferent(resource,builtBean,tagDeclaredField,field);
        tagDeclaredField.setAccessible(false);
    }

    /**
     * 处理不同类型属性，
     * 按照目标对象属性来进行转换
     * @param resource
     * @param builtBean
     * @param tagDeclaredField
     * @param field
     */
    private static <T> void handlerFieldByDifferent(Object resource, T builtBean, Field tagDeclaredField, Field field) throws IllegalAccessException{
        Class<?> fieldType = field.getType();//获取目标对象属性类型
        Object resourceFieldValue = tagDeclaredField.get(resource);//获取资源对象属性的属性value
        if(fieldType.isPrimitive()){
            handlerPrimitiveFiled(builtBean,field,resourceFieldValue);
            return;
        }
        handlerOtherTypeField(builtBean,field,resourceFieldValue);
    }

    /**
     * 处理其他类型的属性
     * @param builtBean 目标对象
     * @param field 目标对象属性
     * @param resourceFieldValue 资源对象对应属性value
     * @param <T> 范型
     */
    private static <T> void handlerOtherTypeField(T builtBean, Field field, Object resourceFieldValue) {


    }

    /**
     * 处理基本类型属性
     * @param builtBean 目标对象
     * @param field 目标对象属性
     * @param resourceFieldValue 被拷贝出来的value
     */
    private static <T> void handlerPrimitiveFiled(T builtBean, Field field, Object resourceFieldValue) {

    }

    /**
     * 创建一个空对象
     * @param tagClass 目标类对象
     * @return 返回一个无属性value的对象
     */
    public static<T> T builtBean(Class<T> tagClass) {
        try {
            return tagClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException |
                 NoSuchMethodException |
                 InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

