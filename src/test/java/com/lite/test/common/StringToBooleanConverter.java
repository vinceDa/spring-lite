package com.lite.test.common;

import com.lite.core.convert.converter.GenericConverter;

import java.util.Collections;
import java.util.Set;

/**
 * @author vince 2024/3/5 16:49
 */
public class StringToBooleanConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Boolean.class));
    }

    @Override
    public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
        return Boolean.valueOf((String) source);
    }
}
