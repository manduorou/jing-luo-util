package com.jingluo.util.ot.date;

import java.util.Date;
import java.util.TimeZone;

/**
 * 时间获取接口
 *
 * @ClassName DateGetter
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public interface DateTer {
    DateTer DATE_HANDLER = new SysDate();
    String defaultFormatRule = "yyyy-MM-dd HH:mm:ss";

    Date local();//获取当前系统时间

    String localDate();//固定格式时间

    TimeZone localTimeZone();//获取时区

    long getLocalTimestamp();//时间戳


    String dataTime();//获取日期+时间

    Date valueOf(String dataStr);//字符转日期

    Date valueOf(long time);//时间戳转日期

    String format(Date date, String patter);//日期格式转换

    /**
     * 获取本地时间
     *
     * @return 本地时间字符串
     */
    static String localDateTime() {
        return DATE_HANDLER.format(DATE_HANDLER.local(), defaultFormatRule);
    }

    /**
     * 格式化日期
     *
     * @param date    目标日期
     * @param pattern 日期格式
     * @return 返回格式化后的日期
     */
    static String parseData(Date date, String pattern) {
        return DATE_HANDLER.format(date, pattern);
    }

    /**
     * 获取当前日期
     * @return 当前日期对象
     */
    static Date localData() {
        return DATE_HANDLER.local();
    }

    /**
     * a日期在b日期后
     * @param A a日期
     * @param B b日期
     * @return
     */
    static boolean AAfterB(Date A,Date B){
        return A.after(B);
    }
}
