package com.jingluo.util.string;

import com.jingluo.util.consts.JLConst;
import com.jingluo.util.CollectionUtils;
import com.jingluo.util.json.JLJson;
import com.jingluo.util.json.JsonUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * toString方法工具类
 * 生成toString模型
 * [className]{fieldName:fieldContent}
 *
 * @ClassName ToStringUtils
 * @Author oldTree
 * @Date 2023/8/10
 * @Version 1.0
 */
@SuppressWarnings("all")
public final class ToStringUtils {
    private final static String LEFT_BRACE = JLConst.LEFT_BRACE;
    private final static String RIGHT_BRACE = JLConst.RIGHT_BRACE;
    private final static String BLANK_STRING = JLConst.BLANK_STRING;
    private final static String LT = JLConst.LT;
    private final static String GT = JLConst.GT;
    private final static String COMMA = JLConst.COMMA;
    private final static String COLON = JLConst.COLON;
    private final static String LEFT_SQUARE_BRACKETS = JLConst.LEFT_SQUARE_BRACKETS;
    private final static String RIGHT_SQUARE_BRACKETS = JLConst.RIGHT_SQUARE_BRACKETS;
    private final static String REPLACE = JLConst.REPLACE;
    private final static String NULL = JLConst.NULL;
    private final static String ALL = JLConst.ALL;
    private final static String EMPTY = JLConst.EMPTY;
    private static final Collection<Class<?>> BASICGROUP = JLConst.basicGroup;

    public static String toString(int value) {
        return String.valueOf(value);
    }

    public static String toString(byte value) {
        return String.valueOf(value);
    }

    public static String toString(short value) {
        return String.valueOf(value);
    }

    public static String toString(long value) {
        return String.valueOf(value);
    }

    public static String toString(char value) {
        return String.valueOf(value);
    }

    /**
     * 外部调用toString，对象转换为固定的string格式
     * 用于显示对象相关的信息
     *
     * @param data 对象数据
     * @param <T>  任意类型
     * @return 转换后的字符串
     */
    public static <T> String toString(T data) {
        StringBuilder content = handlerData(data);
        return content == null ? NULL : content.toString();
    }

    /**
     * TODO 处理对象为json字符串
     *
     * @param data 被处理对象
     * @param <T>  范型数据
     * @return 返回字符串
     */
    public static <T> String toJson(T data) {

        return null;
    }

    /**
     * 转换为字符串并进行输出
     *
     * @param data 对象数据
     * @param <T>  任意类型
     */
    public static <T> void toStringAndPrint(T data) {
        System.out.println(toString(data));
    }

    /**
     * 数据预处理，先判断其类型
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> StringBuilder handlerData(T data) {
        Class<?> type = data.getClass();
        if (type.isPrimitive()) {
            return resolvePrimitiveValueToString(data);
        }
        if (type.isAnnotation()) {
            return resolveAnnotationValueToString(data);
        }
        if (type.isInterface()) {
            return resolveInterFaceValueToString(data);
        }
        if (data instanceof Collection) {
            return resolveCollectValueToString(data);
        }
        if (data instanceof Map) {
            return resolveMapValueToString(data);
        }
        return resolveObjectValueToString(data);
    }

    /**
     * TODO 处理接口对象
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> StringBuilder resolveInterFaceValueToString(T data) {

        return null;
    }

    /**
     * TODO 处理注解
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> StringBuilder resolveAnnotationValueToString(T data) {

        return null;
    }

    /**
     * 处理本地对象
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> StringBuilder resolvePrimitiveValueToString(T data) {
        return new StringBuilder(String.format(REPLACE, data));
    }

    /**
     * 处理集合对象
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> StringBuilder resolveCollectValueToString(T data) {
        Collection collection = (Collection) data;
        if (collection == null) {
            return null;
        }
        return handlerCollectValueToString(collection, resolveDataTypeName(data));
    }

    /**
     * 处理集合对象
     *
     * @param collection
     * @param simpTypeName
     * @param <T>
     * @return
     */
    private static <T> StringBuilder handlerCollectValueToString(Collection collection, String simpTypeName) {
        StringBuilder content = new StringBuilder();
        content.append(content.append(String.format(StringResolve.multiplyBy(REPLACE, 3), LEFT_SQUARE_BRACKETS, resolveDataTypeName(collection), RIGHT_SQUARE_BRACKETS)));
        return content;
    }

    /**
     * 处理map集合对象
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> StringBuilder resolveMapValueToString(T data) {
        Map contentMap = (Map) data;
        if (contentMap == null) {
            return null;
        }
        return handlerMapValueToString(contentMap, resolveDataTypeName(data));
    }

    /**
     * 处理map集合对象
     * [Map]<Object>[{},{}]
     *
     * @param contentMap
     * @param typeName
     * @return
     */
    private static StringBuilder handlerMapValueToString(Map contentMap, String typeName) {
        StringBuilder content = new StringBuilder();
        content.append(String.format(StringResolve.multiplyBy(REPLACE, 3), LEFT_SQUARE_BRACKETS, resolveDataTypeName(contentMap), RIGHT_SQUARE_BRACKETS));
        if (!contentMap.isEmpty()) {
            contentMap.forEach( (key,value) -> {
                content.append(String.format(StringResolve.multiplyBy(REPLACE, 3),key,":",JLJson.parseObjToJson(value)));
            });
        }
        return content;
    }

    private static <T> StringBuilder resolveObjectValueToString(T data) {
        StringBuilder content = new StringBuilder();
        if (BASICGROUP.contains(data.getClass())) {
            handlerBasicObjectValue(content, data);
            return content;
        }
        handlerOtherObjectValue(content, data);
        return content;
    }

    /**
     * 处理基础object对象
     *
     * @param content 字符变量
     * @param data    待转换数据
     * @param <T>     范型
     */
    private static <T> void handlerBasicObjectValue(StringBuilder content, T data) {
        content.append(data);
    }

    private static <T> void handlerOtherObjectValue(StringBuilder content, T data) {
        content.append(String.format(StringResolve.multiplyBy(REPLACE, 3), LEFT_SQUARE_BRACKETS, resolveDataTypeName(data), RIGHT_SQUARE_BRACKETS));
        //处理内容后缀
        content.append(String.format(StringResolve.multiplyBy(REPLACE, 3), LEFT_BRACE, resolveObjectFieldsValue(data), RIGHT_BRACE));
    }


    /**
     * 处理后缀内容,一般涉及对象属性内容
     *
     * @param object
     * @return
     */
    @SuppressWarnings(ALL)
    private static String resolveObjectFieldsValue(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        if (!validateTypeToObjectNotInAssemble(object)) {
            return null;
        }
        StringBuilder fieldContent = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        if (CollectionUtils.nonEmpty(Collections.singletonList(fields))) {
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    handlerDataField(field, object, fieldContent);
                    field.setAccessible(false);
                }
                int indexOf = fieldContent.lastIndexOf(COMMA);
                if (-1 != indexOf) {
                    fieldContent.replace(indexOf, indexOf + 1, BLANK_STRING);
                }
                return fieldContent.toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 处理数据的属性
     *
     * @param field        属性
     * @param object       资源数据
     * @param fieldContent 属性字符拼接
     * @throws IllegalAccessException 异常
     */
    private static void handlerDataField(Field field, Object object, StringBuilder fieldContent) throws IllegalAccessException {
        fieldContent.append(
                String.format(
                        StringResolve.multiplyBy(REPLACE, 4),
                        field.getName(),
                        COLON,
                        field.get(object),
                        COMMA));

    }

    /**
     * 判断object是否是集合数据
     *
     * @param object
     * @return
     */
    private static boolean validateTypeToObjectNotInAssemble(Object object) {
        return !(object instanceof Iterable || object instanceof Map);
    }


    /**
     * 处理前缀内容
     *
     * @param object
     * @return
     */
    private static String resolveDataTypeName(Object object) {
        return object.getClass().getSimpleName();
    }
}

