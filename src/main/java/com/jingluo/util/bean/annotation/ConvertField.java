package com.jingluo.util.bean.annotation;


import com.jingluo.util.bean.convert.CommonConverter;
import com.jingluo.util.bean.convert.BaseConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertField {
    String targetField();

    String convertName() default "";

    CommonConverter commonConverter() default CommonConverter.NONE;

     Class<? extends BaseConverter> customConverter() default BaseConverter.class;
}
