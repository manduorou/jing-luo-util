package com.jingluo.util.json;

import com.jingluo.util.consts.JLConst;
import com.jingluo.util.string.Validator;
import org.apache.commons.lang3.StringUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * json工具类
 *
 * @ClassName JLJson
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public class JsonUtils implements JLJson {
    private static final String EMPTY_JSON = JLConst.EMPTY_JSON;
    private static final String EMPTY_MAP_JSON = JLConst.EMPTY_JSON;
    private static final String JSON_OBJ_ITEM = JLConst.JSON_OBJ_ITEM;
    private static final String JSON_OBJ_BEGIN = JLConst.LEFT_BRACE;
    private static final String NEXT_LINE = JLConst.NEXT_LINE;
    private static final String JSON_OBJ_OVER = JLConst.RIGHT_BRACE;
    private static final String JSON_ITEM_COMMA = JLConst.COMMA;
    private static final String JSON_COLLECT_ITEM = JLConst.JSON_COLLECT_ITEM;
    private static final String EMPTY_JSON_ITEM_VALUE = JLConst.JSON_ITEM_VALUE;
    private static final String EMPTY_STR = JLConst.EMPTY_STR;
    private static int deep = 1;
    private static final Collection<Class<?>> BASIC_CLASS = JLConst.basicGroup;
    private static final Collection<Class<?>> NUMBER_CLASS = JLConst.numberClass;

    protected static String format(String json) {
        return JsonFormatter.format(json);
    }

    protected static <T> String toJson(T data) {
        if (data != null) {
            return resolveData(data);
        }
        return EMPTY_JSON;
    }

    protected static boolean isEmpty(String json) {
        return json == null || StringUtils.equals(json, EMPTY_STR) || StringUtils.equals(json, EMPTY_JSON);
    }

    private static <T> String resolveData(T data) {
        if (BASIC_CLASS.contains(data.getClass())) {
            return String.valueOf(data);
        }
        StringBuilder content = new StringBuilder();
        if (data instanceof Map) {
            return resolveMapMetaToJson(content, data);
        }
        if (data instanceof Collection) {
            return resolveCollectMetaToJson(content, data);
        }
        return handlerObjectPropertiesToJson(content, data);
    }

    private static <K, V, T> String resolveMapMetaToJson(StringBuilder content, T data) {
        Map<K, V> map = (Map) data;
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            content.append(JSON_OBJ_BEGIN).append(NEXT_LINE);
        }
        while (iterator.hasNext()) {
            Map.Entry<K, V> next = iterator.next();
            content.append(String.format(JSON_OBJ_ITEM, next.getKey(), handlerItemValueIsNumberOrStr(next.getValue())));
        }
        if (!iterator.hasNext()) {
            content.append(JSON_OBJ_OVER);
        }
        handlerTheLastDouHao(content);
        return content.length() == 0 ? EMPTY_MAP_JSON : content.toString();
    }

    private static <V> String handlerItemValueIsNumberOrStr(V value) {
        if (value == null) {
            return String.format(EMPTY_JSON_ITEM_VALUE, EMPTY_STR);
        }
        Class<?> valueType = value.getClass();
        StringBuilder returnValue = new StringBuilder();
        if (!NUMBER_CLASS.contains(valueType)) {
            return defaultHandlerItemValueStr(returnValue, value);
        }
        return returnValue.append(value).toString();
    }

    private static <V> String defaultHandlerItemValueStr(StringBuilder returnValue, V value) {
        return returnValue.append(String.format(EMPTY_JSON_ITEM_VALUE, value)).toString();
    }


    private static void handlerTheLastDouHao(StringBuilder content) {
        int index = content.lastIndexOf(JSON_ITEM_COMMA);
        if (index != -1) {
            content.replace(index, index + 1, EMPTY_STR);
        }
    }

    private static <T> String resolveCollectMetaToJson(StringBuilder content, T data) {
        return null;
    }

    private static <T> String handlerObjectPropertiesToJson(StringBuilder content, T data) {
        //初始深度
        deep += 1;
        return null;
    }

    public static class JsonFormatter {
        private static final String SPACE = JLConst.SPACE;
        private static boolean existLeft = false;

        public static String format(String json) {
            if (Validator.validCharSeqIsEmpty(json)) {
                return json;
            }
            return handlerJson(json);
        }

        private static String handlerJson(String json) {
            StringBuilder result = new StringBuilder();
            int length = json.length();
            int number = 0;
            char key = 0;
            for (int i = 0; i < length; i++) {
                key = json.charAt(i);

                if (isEffectSpecChr(i, key, json)) {
                    if ((key == '[') || (key == '{')) {
                        result.append(key);
                        result.append('\n');
                        number++;
                        result.append(indent(number));
                        continue;
                    }

                    if ((key == ']') || (key == '}')) {
                        result.append('\n');
                        number--;
                        result.append(indent(number));
                        result.append(key);
                        continue;
                    }

                    if ((key == ',')) {
                        result.append(key);
                        result.append('\n');
                        result.append(indent(number));
                        continue;
                    }
                }
                result.append(key);
            }
            return result.toString();
        }

        //过滤有效的特殊字符
        private static boolean isEffectSpecChr(int index, char key, String json) {
            boolean flag = false;

            if (isDouMark(index, String.valueOf(key), json)) {
                existLeft = !existLeft;
            } else {
                if ((key == '[')
                        || (key == '{')
                        || (key == ']')
                        || (key == '}')
                        || (key == ',')) {
                    if (!existLeft) {
                        flag = true;
                    }
                }
            }
            return flag;
        }

        //判断当前双引号是否为特殊字符
        private static boolean isDouMark(int index, String key, String json) {
            if (key.equals("\"") && index >= 0) {
                if (index == 0) {
                    return true;
                }

                char c = json.charAt(index - 1);
                if (c != '\\') {
                    return true;
                }
            }
            return false;
        }

        //补充tab空格
        private static String indent(int number) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < number; i++) {
                result.append(SPACE);
            }
            return result.toString();
        }
    }
}

