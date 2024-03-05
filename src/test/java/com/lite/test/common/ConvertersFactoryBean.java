package com.lite.test.common;

import com.lite.beans.factory.FactoryBean;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vince 2024/3/5 17:50
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {
    @Override
    public Set<?> getObject() throws Exception {
        HashSet<Object> converters = new HashSet<>();
        converters.add(new StringToLocalDateConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return converters;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
