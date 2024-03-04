package com.lite.core.convert.converter;

/**
 * 类型转换器接口，适用于一对多的类型转换
 * @author ohyoung 2024/3/4 22:38
 */
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
