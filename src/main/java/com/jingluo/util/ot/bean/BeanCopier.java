package com.jingluo.util.ot.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * bean反射拷贝工具类
 * 高并发不建议使用
 * @ClassName BeanCopier
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public final class BeanCopier {
    private final static Logger logger = LoggerFactory.getLogger(BeanCopier.class);

    public static <T> T convertOne(Object resource, Class<T> tagClass) {
        if (resource == null) {
            return null;
        }
        T builtBean = builtBean(tagClass);
        resolveFields(resource, builtBean, tagClass);
        return builtBean;
    }

    public static <T, K, V> T convertMapToOne(Map<K, V> wrapper, Class<T> tagClass) {
        if (Objects.isNull(wrapper) || wrapper.isEmpty()) {
            return null;
        }
        T tagBean = builtBean(tagClass);
        resolvedWrapperMapToTagBean(wrapper, tagBean, tagClass);
        return tagBean;
    }

    /**
     * 处理映射到普通bean对应属性
     *
     * @param wrapper  映射map
     * @param tagBean  目标bean
     * @param tagClass 目标类对象
     * @param <V>      范型value
     * @param <K>      范型key值
     * @param <T>      范型数据类型
     */
    private static <V, K, T> void resolvedWrapperMapToTagBean(Map<K, V> wrapper, T tagBean, Class<T> tagClass) {
        //迭代器内容
        Iterator<Map.Entry<K, V>> iterator = wrapper.entrySet().iterator();
        logger.debug("开始将map映射到对应bean{},映射数量{}", tagClass.getName(), wrapper.size());
        try {
            while (iterator.hasNext()) {
                Map.Entry<K, V> next = iterator.next();
                //如果对应属性为null，跳过本次
                V value = next.getValue();
                String key = String.valueOf(next.getKey());
                if (value == null) {
                    continue;
                }
                //获取key，通过key去目标对象对比属性
                //如果属性的值存在，则赋予
                Field declaredField = tagClass.getDeclaredField(key);
                logger.debug("映射属性{}，对应值{}", key, value);
                declaredField.setAccessible(true);
                handlerWrapperToField(declaredField, tagBean, value);
                declaredField.setAccessible(false);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("反射对象属性异常信息:{}", e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    private static <T, V> void handlerWrapperToField(Field declaredField, T tagBean, V value) throws IllegalAccessException {
        Class<?> aClass = declaredField.getType();
        if (aClass.equals(String.class)) {
            declaredField.set(tagBean, String.valueOf(value));
        } else if (aClass.equals(Integer.class)) {
            declaredField.set(tagBean, Integer.valueOf(String.valueOf(value)));
        } else if (aClass.equals(Long.class)) {
            declaredField.set(tagBean, Long.valueOf(String.valueOf(value)));
        }
    }

    /**
     * 拷贝集合中对象的属性
     *
     * @param resources 集合对象
     * @param tagClass  拷贝生成的类对象
     * @param <T>       范型
     * @return 返回拷贝后的集合对象
     */
    public static <T, O> Collection<T> convertCollection(Collection<O> resources, Class<T> tagClass) {
        Collection<T> collection = new ArrayList<>();
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        resources.forEach(resource -> {
            collection.add(convertOne(resource, tagClass));
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
     *
     * @param resource  资源对象
     * @param builtBean 目标对象
     * @param tagClass  目标对象类
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
     *
     * @param resource  资源数据
     * @param builtBean 目标数据
     * @param field     目标数据中的属性
     * @param <T>       返回对应类型
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     * @throws NoSuchMethodException     异常
     * @throws InstantiationException    异常
     */
    private static <T> void handlerFieldValue(Object resource, T builtBean, Field field) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
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
        if (tagDeclaredField.getType().equals(field.getType())) {
            Object value = tagDeclaredField.get(resource);
            field.set(builtBean, value);
            return;
        }
        handlerFieldByDifferent(resource, builtBean, tagDeclaredField, field);
        tagDeclaredField.setAccessible(false);
    }

    /**
     * 处理不同类型属性，
     * 按照目标对象属性来进行转换
     *
     * @param resource 实体参照
     * @param builtBean bean实体
     * @param tagDeclaredField 目标属性
     * @param field 待处理属性
     */
    private static <T> void handlerFieldByDifferent(Object resource, T builtBean, Field tagDeclaredField, Field field) throws IllegalAccessException {
        Class<?> fieldType = field.getType();//获取目标对象属性类型
        Object resourceFieldValue = tagDeclaredField.get(resource);//获取资源对象属性的属性value
        if (fieldType.isPrimitive()) {
            handlerPrimitiveFiled(builtBean, field, resourceFieldValue);
            return;
        }
        handlerOtherTypeField(builtBean, field, resourceFieldValue);
    }

    /**
     * TODO 处理其他类型的属性
     *
     * @param builtBean          目标对象
     * @param field              目标对象属性
     * @param resourceFieldValue 资源对象对应属性value
     * @param <T>                范型
     */
    private static <T> void handlerOtherTypeField(T builtBean, Field field, Object resourceFieldValue) {


    }

    /**
     * TODO 处理基本类型属性
     *
     * @param builtBean          目标对象
     * @param field              目标对象属性
     * @param resourceFieldValue 被拷贝出来的value
     */
    private static <T> void handlerPrimitiveFiled(T builtBean, Field field, Object resourceFieldValue) {

    }

    /**
     * 创建一个空对象
     *
     * @param tagClass 目标类对象
     * @return 返回一个无属性value的对象
     */
    public static <T> T builtBean(Class<T> tagClass) {
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

