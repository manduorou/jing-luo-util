//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jingluo.util.bean;

import com.jingluo.util.bean.annotation.ConvertBean;
import com.jingluo.util.bean.annotation.ConvertField;
import com.jingluo.util.bean.convert.BaseConverter;
import com.jingluo.util.bean.convert.CommonConverter;
import com.jingluo.util.bean.exception.BeanCopyException;
import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
/**
 * 对象属性转换/拷贝工具类
 * @ClassName BeanConvertUtil
 * @Author 鲸落网络-oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
@SuppressWarnings("all")
@Component
public class BeanConvertUtil {
    private static final Logger log = LoggerFactory.getLogger(BeanConvertUtil.class);
    private static final BeanConvertUtil beanCopyUtilsInstance = new BeanConvertUtil();

    public static final BeanConvertUtil build() {
        return beanCopyUtilsInstance;
    }

    private final MapperFactory mapperFactory = this.mapperFacadeConfigure();
    private ConcurrentHashMap<Class<? extends BaseConverter>, String> baseConverterMap = new ConcurrentHashMap<>();
    private List<String> commNames = Arrays.stream(CommonConverter.values())
            .map(CommonConverter::converterName)
            .filter(Objects::nonNull).collect(Collectors.toList());

    private BeanConvertUtil() {
    }

    private MapperFactory mapperFacadeConfigure() {
        return this.initCommonConvertConfig();
    }

    private MapperFactory initCommonConvertConfig() {
        MapperFactory mapperFactory = (new DefaultMapperFactory.Builder()).build();
        Arrays.stream(CommonConverter.values()).filter((common) -> {
            return common != CommonConverter.NONE;
        }).forEach((common) -> {
            mapperFactory.getConverterFactory().registerConverter(common.converterName(), common.converter());
        });
        return mapperFactory;
    }

    public <S, T> T convertTo(S source, Class<T> clazz) {
        if (source == null) {
            return null;
        } else {
            MapperFacade mapperFacade = this.initMapperFacade(source.getClass(), clazz);
            return mapperFacade.map(source, clazz);
        }
    }

    public <S, T> List<T> convertToList(List<S> sourceList, Class<T> clazz) {
        if (sourceList == null) {
            return null;
        } else if (sourceList.isEmpty()) {
            return new ArrayList();
        } else {
            MapperFacade mapperFacade = this.initMapperFacade(sourceList.get(0).getClass(), clazz);
            return mapperFacade.mapAsList(sourceList, clazz);
        }
    }

    private <S, T> MapperFacade initMapperFacade(Class<S> source, Class<T> target) {
        ConvertDirectionEntity convertDirectionEntity = this.getConvertDirection(source, target);
        log.debug("准备进行A类型{}和B类型{}的复制", source, target);
        ClassMapBuilder classMapBuilder;
        if (convertDirectionEntity.convertDirection == ConvertDirection.A_TO_B) {
            log.debug("按照A{}的转换标准进行", source);
            this.registerCommonConverter(source);
            classMapBuilder = this.registerFieldMap(source, this.mapperFactory.classMap(source, target));
        } else if (convertDirectionEntity.convertDirection == ConvertDirection.B_TO_A) {
            log.debug("按照B{}的转换标准进行", target);
            this.registerCommonConverter(target);
            classMapBuilder = this.registerFieldMap(target, this.mapperFactory.classMap(target, source));
        } else {
            classMapBuilder = this.mapperFactory.classMap(source, target);
        }

        if (!this.mapperFactory.existsRegisteredMapper(classMapBuilder.getAType(), classMapBuilder.getBType(), false)) {
            classMapBuilder.byDefault(new DefaultFieldMapper[0]).register();
        }

        return this.mapperFactory.getMapperFacade();
    }

    private <S, T> ConvertDirectionEntity getConvertDirection(Class<S> source, Class<T> target) {
        if (this.isConvertFunctionClass(source, target)) {
            return new ConvertDirectionEntity(source, ConvertDirection.A_TO_B);
        } else {
            return this.isConvertFunctionClass(target, source) ? new ConvertDirectionEntity(target, ConvertDirection.B_TO_A) : new ConvertDirectionEntity((Class) null, ConvertDirection.NONE);
        }
    }

    private <S, T> boolean isConvertFunctionClass(Class<S> source, Class<T> target) {
        ConvertBean sourceConvertBean = (ConvertBean) source.getDeclaredAnnotation(ConvertBean.class);
        if (sourceConvertBean == null) {
            return false;
        } else {
            return sourceConvertBean.convertClass().equals(Void.TYPE) || target.equals(sourceConvertBean.convertClass());
        }
    }

    private <A, B> ClassMapBuilder<A, B> registerFieldMap(Class<?> clazz, ClassMapBuilder<A, B> classMapBuilder) {
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).filter((field) -> field.getAnnotation(ConvertField.class) != null).forEach((field) -> {
            ConvertField cf = field.getAnnotation(ConvertField.class);
            if (Modifier.isAbstract(cf.customConverter().getModifiers()) && cf.commonConverter() == CommonConverter.NONE && cf.convertName().isEmpty()) {
                classMapBuilder.field(field.getName(), cf.targetField());
            } else {
                String convertName = cf.convertName().isEmpty() ? this.getConverterName(cf) : cf.convertName();
                if (!this.existConverterName(convertName)) {
                    log.warn("不存在名称为{}的转换器，请检查是否已经扫描进容器当中", convertName);
                }

                classMapBuilder.fieldMap(field.getName(), cf.targetField()).converter(convertName).add();
            }

        });
        return classMapBuilder;
    }

    private void registerCommonConverter(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredFields()).filter((field) -> {
            ConvertField cf = field.getAnnotation(ConvertField.class);
            if (cf != null && cf.commonConverter() != CommonConverter.NONE) {
                String convertName = this.getConverterName(cf);
                return !this.mapperFactory.getConverterFactory().hasConverter(convertName);
            } else {
                return false;
            }
        }).map((field) -> field.getAnnotation(ConvertField.class)).forEach((convertField) -> {
            String convertName = this.getConverterName(convertField);
            this.mapperFactory.getConverterFactory().registerConverter(convertName, convertField.commonConverter().converter());
        });
    }

    public void registerBaseConverter(Class<? extends BaseConverter> baseConverter) {
        try {
            BaseConverter baseConverterInstance = baseConverter.getDeclaredConstructor().newInstance();
            String convertName = (String) baseConverter.getMethod("convertName").invoke(baseConverterInstance);
            this.mapperFactory.getConverterFactory().registerConverter(convertName, baseConverterInstance);
            this.baseConverterMap.put(baseConverter, convertName);
            log.debug("添加转换器：{}:{}", convertName, baseConverter.getName());
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException var4) {
            var4.printStackTrace();
            log.error(var4.getMessage());
            throw new BeanCopyException("反射转换类型时出现错误", var4);
        }
    }

    private String getConverterName(ConvertField cf) {
        if (Modifier.isAbstract(cf.customConverter().getModifiers())) {
            return cf.commonConverter().converterName();
        } else {
            if (!this.baseConverterMap.containsKey(cf.customConverter())) {
                this.registerBaseConverter(cf.customConverter());
            }

            return this.baseConverterMap.get(cf.customConverter());
        }
    }

    private boolean existConverterName(String converterName) {
        return this.commNames.contains(converterName) || this.baseConverterMap.values()
                .stream()
                .anyMatch((value) -> value.equals(converterName));
    }

    public static enum ConvertDirection {
        A_TO_B,
        B_TO_A,
        NONE;

        ConvertDirection() {
        }
    }

    private class ConvertDirectionEntity {
        Class<?> convertDirectionClazz;
        ConvertDirection convertDirection;

        ConvertDirectionEntity(Class<?> convertDirectionClazz, ConvertDirection convertDirection) {
            this.convertDirectionClazz = convertDirectionClazz;
            this.convertDirection = convertDirection;
        }
    }
}
