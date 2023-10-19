//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jingluo.util.ot.bean.convert;

import com.jingluo.util.ot.bean.BeanConvertUtil;
import com.jingluo.util.ot.bean.annotation.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Arrays;
import java.util.Set;

public class ConvertClassPathScanner extends ClassPathBeanDefinitionScanner {
    private static final Logger log = LoggerFactory.getLogger(ConvertClassPathScanner.class);

    public ConvertClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public ConvertClassPathScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public ConvertClassPathScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public ConvertClassPathScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        this.addIncludeFilter(new AnnotationTypeFilter(Converter.class));
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            log.warn("No DgbSecurity Spring Componet was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        }
        BeanConvertUtil beanCopyUtils = BeanConvertUtil.build();
        beanDefinitions.forEach((bean) -> {
            GenericBeanDefinition definition = (GenericBeanDefinition)bean.getBeanDefinition();
            try {
                Class converter = Class.forName(definition.getBeanClassName());
                if (BaseConverter.class.isAssignableFrom(converter)) {
                    beanCopyUtils.registerBaseConverter(converter);
                    log.debug("将转换器{}加载进缓存");
                }
            } catch (ClassNotFoundException var4) {
                log.error("没有获取到对应的javabean信息：{}", var4.getException().getMessage());
                var4.printStackTrace();
            }

        });
        return beanDefinitions;
    }
}
