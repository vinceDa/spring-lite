package com.lite.core.convert;

/**
 * @author ohyoung 2024/3/4 22:52
 */
public interface ConversionService {

    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);

}
