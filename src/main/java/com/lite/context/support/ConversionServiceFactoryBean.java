package com.lite.context.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.FactoryBean;
import com.lite.beans.factory.InitializingBean;
import com.lite.core.convert.ConversionService;
import com.lite.core.convert.converter.Converter;
import com.lite.core.convert.converter.ConverterFactory;
import com.lite.core.convert.converter.ConverterRegistry;
import com.lite.core.convert.converter.GenericConverter;
import com.lite.core.convert.support.DefaultConversionService;
import com.lite.core.convert.support.GenericConverterService;

import java.util.Objects;
import java.util.Set;

/**
 * @author vince 2024/3/5 17:44
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    private Set<?> converters;

    private GenericConverterService converterService;

    @Override
    public ConversionService getObject() throws Exception {
        return converterService;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        converterService = new DefaultConversionService();
        registerConverters(converters, converterService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (Objects.isNull(converters)) {
            return;
        }

        for (Object converter : converters) {
            if (converter instanceof GenericConverter) {
                registry.addConverter((GenericConverter) converter);
            } else if (converter instanceof Converter<?, ?>) {
                registry.addConverter((Converter<?, ?>) converter);
            } else if (converter instanceof ConverterFactory<?,?>) {
                registry.addConverterFactory((ConverterFactory<?, ?>) converter);
            } else {
                throw new IllegalArgumentException("Each converter object must implement one of the " +
                        "Converter, ConverterFactory, or GenericConverter interfaces");
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
