package com.jingluo.util.consts;

import com.jingluo.util.string.StringResolve;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 各种常量
 *
 * @ClassName JLConsts
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public class JLConst {
    public final static String LEFT_BRACE = "{";
    public final static String RIGHT_BRACE = "}";
    public final static String BLANK_STRING = "";
    public final static String LT = "<";
    public final static String GT = ">";
    public final static String COMMA = ",";
    public final static String COLON = ":";
    public final static String LEFT_SQUARE_BRACKETS = "[";
    public final static String RIGHT_SQUARE_BRACKETS = "]";
    public final static String REPLACE = "%s";
    public final static String NULL = "null";
    public final static String ALL = "all";
    public final static String EMPTY = "empty";
    public static final String EMPTY_JSON = "{}";
    public static final String JSON_COLLECT_ITEM = "[]";
    public static final String EMPTY_STR = "";
    public static final String SPACE = StringResolve.multiplyBy(" ", 3);

    public static final Collection<Class<?>> basicGroup;
    public static final String JSON_OBJ_ITEM = "  \"%s\": %s,\n";
    public static final Collection<Class<?>> numberClass;
    public static final String JSON_ITEM_VALUE = "\"%s\"";
    public static final String NEXT_LINE = "\n";

    static {
        basicGroup = new ArrayList<>();
        basicGroup.add(Integer.class);
        basicGroup.add(Number.class);
        basicGroup.add(int.class);
        basicGroup.add(Double.class);
        basicGroup.add(double.class);
        basicGroup.add(Float.class);
        basicGroup.add(float.class);
        basicGroup.add(Long.class);
        basicGroup.add(long.class);
        basicGroup.add(Short.class);
        basicGroup.add(short.class);
        basicGroup.add(BigDecimal.class);
        basicGroup.add(Byte.class);
        basicGroup.add(byte.class);
        basicGroup.add(CharSequence.class);
        basicGroup.add(String.class);
        basicGroup.add(char.class);
        basicGroup.add(Character.class);
        basicGroup.add(Boolean.class);
        basicGroup.add(boolean.class);
        basicGroup.add(Date.class);
        basicGroup.add(Serializable.class);

        numberClass = new ArrayList<>();
        numberClass.add(Integer.class);
        numberClass.add(Number.class);
        numberClass.add(int.class);
        numberClass.add(Double.class);
        numberClass.add(double.class);
        numberClass.add(Float.class);
        numberClass.add(float.class);
        numberClass.add(Long.class);
        numberClass.add(long.class);
        numberClass.add(Short.class);
        numberClass.add(short.class);
        numberClass.add(BigDecimal.class);
        numberClass.add(Byte.class);
        numberClass.add(byte.class);
    }
}

