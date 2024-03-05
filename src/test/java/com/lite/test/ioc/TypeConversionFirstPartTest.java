package com.lite.test.ioc;

import com.lite.core.convert.converter.Converter;
import com.lite.core.convert.support.GenericConverterService;
import com.lite.core.convert.support.StringToNumberConverterFactory;
import com.lite.test.common.StringToBooleanConverter;
import com.lite.test.common.StringToIntegerConverter;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/3/5 16:48
 */
public class TypeConversionFirstPartTest {

    @Test
    public void testStringToIntegerConverter() {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        assert stringToIntegerConverter.convert("2").equals(2);
    }

    @Test
    public void testStringToNumberConverterFactory() {
        StringToNumberConverterFactory stringToNumberConverterFactory = new StringToNumberConverterFactory();
        Converter<String, Integer> String2Integer = stringToNumberConverterFactory.getConverter(Integer.class);
        assert String2Integer.convert("1").equals(1);

        Converter<String, Long> String2Long = stringToNumberConverterFactory.getConverter(Long.class);
        assert String2Long.convert("1").equals(1L);
    }

    @Test
    public void testStringToBoolean() {
        StringToBooleanConverter stringToBooleanConverter = new StringToBooleanConverter();
        assert stringToBooleanConverter.convert("true", String.class, Boolean.class).equals(true);
    }

    @Test
    public void testGenericConversionService() {
        GenericConverterService genericConverterService = new GenericConverterService();
        genericConverterService.addConverter(new StringToIntegerConverter());
        assert genericConverterService.canConvert(String.class, Integer.class);
        assert genericConverterService.convert("1", Integer.class).equals(1);

        genericConverterService.addConverter(new StringToBooleanConverter());
        assert genericConverterService.canConvert(String.class, Boolean.class);
        assert genericConverterService.convert("true", Boolean.class).equals(true);

        genericConverterService.addConverterFactory(new StringToNumberConverterFactory());
        assert genericConverterService.canConvert(String.class, Long.class);
        assert genericConverterService.convert("1", Long.class).equals(1L);
    }

}
