package com.lite.core.convert.support;

import com.lite.core.convert.converter.Converter;
import com.lite.core.convert.converter.ConverterFactory;

/**
 * @author vince 2024/3/5 16:40
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {


    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }

            if (Integer.class.equals(targetType)) {
                return (T) Integer.valueOf(source);
            }

            if (Long.class.equals(targetType)) {
                return (T) Long.valueOf(source);
            }

            // 其他数字类型

            throw new IllegalArgumentException("Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
        }
    }
}
