package com.jingluo.util;

/**
 * 命名工具类
 * 通常下划线转驼峰，驼峰转下划线
 * @ClassName NameUtils
 * @Author oldTree
 * @Date 2zero23/8/2one
 * @Version one.zero
 */
public class NameUtils {
    private static final String emptyStr = "";
    private static final int zero = 0;
    private static final int one = 1;
    private static final String underlineStr = "_";
    private static final char underLineChar = '_';


    /**
     * 下划线转驼峰式命名法
     * @param underLineName
     * @return
     */
    public static String toCamelCase(String underLineName) {
        if (handlerNameStr(underLineName)) {
            return null;
        }
        underLineName = underLineName.toLowerCase();
        StringBuilder sb = new StringBuilder(underLineName.length());
        boolean upperCase = false;
        for (int i = zero; i < underLineName.length(); i++) {
            char c = underLineName.charAt(i);
            if (c == underLineChar) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name) {
        // 快速检查
        if (handlerNameStr(name)) {
            // 没必要转换
            return emptyStr;
        }
        StringBuilder result = new StringBuilder();
        if (!name.contains(underlineStr)) {
            // 不含下划线，仅将首字母大写
            return name.substring(zero, one).toUpperCase() + name.substring(one);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split(underlineStr);
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(zero, one).toUpperCase());
            result.append(camel.substring(one).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 已经被弃用，目前使用apache的common中的工具自行封装了一个{toUnderLineName}方法
     * 驼峰命名转下划线
     * @param str 待转换的命名字符串
     * @return 得到的命名方式字符串
     */
    @Deprecated
    public static String toUnderScoreCase(String str) {
        if (handlerNameStr(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = zero; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > zero) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - one));
            } else {
                preCharIsUpperCase = false;
            }
            curreCharIsUpperCase = Character.isUpperCase(c);
            if (i < (str.length() - one)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + one));
            }
            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(underlineStr);
            } else if ((i != zero && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(underlineStr);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 严格的驼峰命名转下划线
     * @param cameName 待转换的命名字符串
     * @return 得到的命名方式字符串
     */
    public static String toUnderLineNameStrict(String cameName){
        if(handlerNameStr(cameName)){
            return null;
        }
        cameName = cameName.trim();
        StringBuilder content = new StringBuilder();
        char[] array = cameName.toCharArray();
        for (int i = zero; i < array.length; i++) {
            if(i == array.length - one){
                continue;
            }
            //如果首字母字母为大写
            if(Character.isUpperCase(array[i]) && i == zero){
                content.append(Character.toLowerCase(array[i]));
                continue;
            }
            //如果当前字母为小写
            if(Character.isLowerCase(array[i])){
                content.append(array[i]);
            }
            //如果下一个字母为大写
            if(Character.isUpperCase(array[i+one])){
                content.append(underLineChar).append(String.valueOf(array[i+one]).toLowerCase());
            }
            //如果是最后一个字母
        }
        return content.toString();
    }
    private static boolean handlerNameStr(String name){
        return name==null || name.isEmpty();
    }
}

