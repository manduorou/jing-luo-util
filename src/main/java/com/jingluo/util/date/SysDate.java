package com.jingluo.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 系统日期
 * @ClassName DataUtils
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public final class SysDate extends Date implements Dater {
    public SysDate() {
    }

    public SysDate(long date) {
        super(date);
    }


    @Override
    public Date local() {
        return new SysDate();
    }

    @Override
    public String localDate() {
        return new SysDate().toString();
    }

    @Override
    public TimeZone localTimeZone() {
        return TimeZone.getDefault();
    }

    @Override
    public long getLocalTimestamp() {
        return new SysDate().getTime();
    }

    @Override
    public String dataTime() {
        return null;
    }

    @Override
    public Date valueOf(String dataStr) {
        try {
            return DateFormat.getDateInstance().parse(dataStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Date valueOf(long time) {
        SysDate sysDate = new SysDate();
        sysDate.setTime(time);
        return sysDate;
    }

    @Override
    public String format(Date date, String patter) {
        return new SimpleDateFormat(patter).format(date);
    }
}

