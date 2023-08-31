package com.jingluo.util.ot.logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 日志输出消息处理器
 *
 * @ClassName MessageHandler
 * @Author oldTree
 * @Date 2023/8/22
 * @Version 1.0
 */
@SuppressWarnings("all")
public class MessageHandler<T> {
    private boolean validate;
    private StringBuilder scopeContent;

    public MessageHandler() {
        this.scopeContent = new StringBuilder();
        validate = false;
    }

    public String handlerMessage(Console.LogLevelEnum levelEnum, String messageSpace) {
        validate = validateStringAndReturn(messageSpace);
        if (!validate) {
            removeAll(scopeContent);
            scopeContent.append(String.format(Objects.requireNonNull(StringConn.multiplyBy(PlaceString.PER_CENT_STRING.value, 3)),
                    levelEnum.level.start,
                    messageSpace,
                    levelEnum.level.end
            ));
            return scopeContent.toString();
        }
        return null;
    }

    /**
     * 消息格式化
     *
     * @param preffix 前缀
     * @param message 消息
     * @param suffix  后缀
     * @return 格式化过后的消息
     */
    public String messageFormat(String preffix, String message, String suffix) {
        if (message == null || message.isEmpty()) {
            return null;
        }
        return new StringBuffer()
                .append(String.format(
                        StringConn.multiplyBy(PlaceString.PER_CENT_STRING.value, 3),
                        preffix, message, suffix))
                .toString();
    }

    public String handlerMessage(Console.LogLevelEnum levelEnum, String messageSpace, T data) {
        validate = validateStringAndReturn(messageSpace);
        if (!validate) {
            removeAll(scopeContent);
            scopeContent.append(String.format(
                    levelEnum.level.start,
                    convertoCaseMsg(messageSpace, data),
                    levelEnum.level.end
            ));
        }
        return null;
    }

    private String convertoCaseMsg(String messageSpace, T data) {
        if (messageSpace.indexOf(PlaceString.CASE_STRING.value) != -1) {
            return messageSpace.replace(PlaceString.CASE_STRING.value, objToString(data));
        }
        return null;
    }

    private CharSequence objToString(T data) {
        StringBuffer toStr = null;
        if (data == null) {
            return null;
        }
        if (data instanceof Object) {
            return toStr.append(resolveObject(data));
        }
        if (data instanceof Iterable) {
            return toStr.append(resolveInterable((List<Object>) data));
        }
        if (data instanceof Map) {
            return toStr.append(resolveMap((Map<String, Object>) data));
        }
        return toStr;
    }

    private CharSequence resolveMap(Map<String, Object> data) {
        return data.toString();
    }

    private CharSequence resolveInterable(List<Object> data) {
        return data.toString();
    }

    private CharSequence resolveObject(T data) {
        return data.toString();
    }

    public String handlerCaseMessage(Console.LogLevelEnum level, String caseMsg, Object... objects) {

        return null;
    }

    public String handlerList(Console.LogLevelEnum level, List<T> list) {

        return null;
    }

    public String handlerObject(Console.LogLevelEnum level, T object) {

        return null;
    }

    private void removeAll(StringBuilder scopeContent) {
        scopeContent.delete(0, scopeContent.length());
    }

    private boolean validateStringAndReturn(String message) {
        return message == null || message.isEmpty();
    }

    public boolean validateObjectAndReturn(T data) {

        return false;
    }

    public boolean validateValueAndReturn(T value) {

        return false;
    }

    @SuppressWarnings("all")
    private static class StringConn {
        private static StringBuilder data;

        public static String multiplyBy(String resource, int num) {
            if (data == null || data.length() == 0) {
                synchronized (StringConn.class) {
                    data = new StringBuilder();
                }
            } else {
                data.delete(0, data.length());
            }
            if (resource != null && !resource.isEmpty() && num > 0) {
                for (int i = 0; i < num; i++) {
                    data.append(resource);
                }
                return data.toString();
            }
            return null;
        }
    }

    /**
     * 替换的枚举字符
     */
    enum PlaceString {
        EMPTY(""),
        DOLOR_STRING("$"),
        CASE_STRING("{}"),
        QUESTION_STRING("?"),
        PER_CENT_STRING("%s"),
        INFO_PREFIX("==> [info ]:\t"),
        DEBUG_PREFIX("==> [debug]:\t"),
        WARN_PREFIX("==> [warn ]:\t"),
        ERROR_PREFIX("==> [error]:\t"),
        FATAL_PREFIX("==> [fatal]:\t"),
        ;
        public final String value;
        PlaceString(String value) {
            this.value = value;
        }
    }
}

