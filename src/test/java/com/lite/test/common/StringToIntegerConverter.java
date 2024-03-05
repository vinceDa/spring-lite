package com.lite.test.common;

import com.lite.core.convert.converter.Converter;

/**
 * @author vince 2024/3/5 16:48
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
