package com.lite.core.convert.converter;

/**
 * @author ohyoung 2024/3/4 22:50
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

    void addConverter(GenericConverter converter);

}
