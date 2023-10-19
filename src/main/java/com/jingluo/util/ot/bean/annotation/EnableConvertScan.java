package com.jingluo.util.ot.bean.annotation;

import com.jingluo.webservice.common.utils.jing_luo.bean.convert.ConvertScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ConvertScannerRegistrar.class})
public @interface EnableConvertScan {
    String[] basePackages() default {""};
}
