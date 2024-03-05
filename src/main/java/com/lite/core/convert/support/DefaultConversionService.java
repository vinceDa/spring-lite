package com.lite.core.convert.support;

import com.lite.core.convert.converter.ConverterRegistry;

/**
 * @author ohyoung 2024/3/4 22:51
 */
public class DefaultConversionService extends GenericConverterService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());

        // 添加其他的ConverterFactory
    }
}
