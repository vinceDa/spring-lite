package com.lite.core.convert.converter;

/**
 *  类型转换器接口，适用于一对一的类型转换
 * @author ohyoung 2024/3/4 22:36
 */
public interface Converter<S, T> {

    /**
     * 类型转换
     */
    T convert(S source);

}
