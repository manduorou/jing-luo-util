package com.jingluo.util.bean.annotation;

import com.jingluo.util.bean.convert.ConvertScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ConvertScannerRegistrar.class})
public @interface EnableConvertScan {
    String[] basePackages() default {""};
}
