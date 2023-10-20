//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jingluo.util.bean.convert;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public enum CommonConverter {
    NONE(null, null),
    CUSTOMER_MONEY_FORMAT("customerMoneyFormat", new BidirectionalConverter<String, Double>() {
        public Double convertTo(String source, Type<Double> destinationType, MappingContext mappingContext) {
            return FormatConverter.stringFormatDouble().convertValue("#,###.##", source);
        }

        public String convertFrom(Double source, Type<String> destinationType, MappingContext mappingContext) {
            return FormatConverter.doubleFormatString().convertValue("#,###.##", source);
        }
    }),
    TIMESTAMP_TO_LOCAL_DATETIME("timeStampToLocalDateTime", new BidirectionalConverter<Long, LocalDateTime>() {
        public LocalDateTime convertTo(Long source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
            return FormatConverter.timeStampForDateTime().convertValue(null, source);
        }

        public Long convertFrom(LocalDateTime source, Type<Long> destinationType, MappingContext mappingContext) {
            return FormatConverter.dateTime2Stamp().convertValue(null, source);
        }
    }),
    DEFAULT_LOCAL_DATE_FORMAT("localDateFormat", new BidirectionalConverter<String, LocalDate>() {
        public LocalDate convertTo(String source, Type<LocalDate> destinationType, MappingContext mappingContext) {
            return FormatConverter.dateFormat().convertValue("yyyyMMdd", source);
        }

        public String convertFrom(LocalDate source, Type<String> destinationType, MappingContext mappingContext) {
            return FormatConverter.dateParse().convertValue("yyyyMMdd", source);
        }
    }),
    DEFAULT_LOCAL_DATE_TIME_FORMAT("localDateTimeFormat", new BidirectionalConverter<String, LocalDateTime>() {
        public LocalDateTime convertTo(String source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
            return FormatConverter.dateTimeFormat().convertValue("yyyyMMdd HH:mm:ss", source);
        }

        public String convertFrom(LocalDateTime source, Type<String> destinationType, MappingContext mappingContext) {
            return FormatConverter.dateTimeParse().convertValue("yyyyMMdd HH:mm:ss", source);
        }
    });

    private String converterName;
    private BidirectionalConverter converter;
    private String dateFormat;

    private CommonConverter(String converterName, BidirectionalConverter converter) {
        this.converterName = converterName;
        this.converter = converter;
    }

    public String converterName() {
        return this.converterName;
    }

    public BidirectionalConverter converter() {
        return this.converter;
    }
}
