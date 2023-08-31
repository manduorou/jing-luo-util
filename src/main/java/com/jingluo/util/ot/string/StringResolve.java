package com.jingluo.util.ot.string;

/**
 * 字符串工具
 * @ClassName StringUtils
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
@SuppressWarnings("all")
public class StringResolve {
    /**
     * 字符按照num的数量相乘拼接
     * @param resource 待乘的字符
     * @param num 数量
     * @return
     */
    public static String multiplyBy(String resource, int num) {
        StringBuilder data = new StringBuilder();
        if (data == null || data.length() == 0) {
            synchronized (StringResolve.class) {
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

