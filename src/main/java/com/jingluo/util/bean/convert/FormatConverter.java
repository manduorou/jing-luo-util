//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jingluo.util.bean.convert;


import com.jingluo.util.bean.exception.BeanCopyException;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;

@FunctionalInterface
public interface FormatConverter<T, R> {
    R convertValue(String var1, T var2);

    static FormatConverter<Double, String> doubleFormatString() {
        return (formatPattern, source) -> {
            return (new DecimalFormat(formatPattern)).format(source);
        };
    }

    static FormatConverter<String, Double> stringFormatDouble() {
        return (formatPattern, source) -> {
            try {
                return (new DecimalFormat(formatPattern)).parse(source).doubleValue();
            } catch (ParseException var3) {
                throw new BeanCopyException("自定义类型转换出错！", var3);
            }
        };
    }

    static FormatConverter<String, LocalDate> dateFormat() {
        return (formatPattern, source) -> {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(formatPattern));
        };
    }

    static FormatConverter<LocalDate, String> dateParse() {
        return (formatPattern, source) -> {
            return DateTimeFormatter.ofPattern(formatPattern).format(source);
        };
    }

    static FormatConverter<String, LocalDateTime> dateTimeFormat() {
        return (formatPattern, source) -> {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(formatPattern));
        };
    }

    static FormatConverter<LocalDateTime, String> dateTimeParse() {
        return (formatPattern, source) -> {
            return DateTimeFormatter.ofPattern(formatPattern).format(source);
        };
    }

    static FormatConverter<String, LocalTime> timeFormat() {
        return (formatPattern, source) -> {
            return LocalTime.parse(source, DateTimeFormatter.ofPattern(formatPattern));
        };
    }

    static FormatConverter<LocalTime, String> timeParse() {
        return (formatPattern, source) -> {
            return DateTimeFormatter.ofPattern(formatPattern).format(source);
        };
    }

    static FormatConverter<LocalDateTime, Long> dateTime2Stamp() {
        return (number, dateTime) -> {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = dateTime.atZone(zone).toInstant();
            return instant.toEpochMilli();
        };
    }

    static FormatConverter<Long, LocalDateTime> timeStampForDateTime() {
        return (dateTime, number) -> {
            Instant instant = Instant.ofEpochMilli(number);
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        };
    }
}
