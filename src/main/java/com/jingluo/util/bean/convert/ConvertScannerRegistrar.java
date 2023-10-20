package com.jingluo.util.bean.convert;

import com.jingluo.util.bean.annotation.EnableConvertScan;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ConvertScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(ConvertScannerRegistrar.class);
    private ResourceLoader resourceLoader;
    private Environment environment;

    public ConvertScannerRegistrar() {
    }

    public void setEnvironment(@NotNull Environment environment) {
        this.environment = environment;
    }

    public void setResourceLoader(@NotNull ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableConvertScan.class.getName()));
        if (annoAttrs != null) {
            ConvertClassPathScanner scanner = new ConvertClassPathScanner(beanDefinitionRegistry);
            if (this.resourceLoader != null) {
                scanner.setResourceLoader(this.resourceLoader);
            }
            List<String> basePackages = new ArrayList<>();
            String[] basePackagesStringArr = annoAttrs.getStringArray("basePackages");
            for (String pkg : basePackagesStringArr) {
                if (StringUtils.hasText(pkg)) {
                    basePackages.add(pkg);
                }
            }
            scanner.doScan(StringUtils.toStringArray(basePackages));
        }

    }
}
