package com.jingluo.util.ot;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 反射相关工具
 * @ClassName EchoUtils
 * @Author oldTree
 * @Date 2023/8/27
 * @Version 1.0
 */
public class EchoUtils {
    public static String[] getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            if (!param.isNamePresent()) {
                return null;
            }
            parameterNames[i] = param.getName();
        }
        return parameterNames;
    }

}

