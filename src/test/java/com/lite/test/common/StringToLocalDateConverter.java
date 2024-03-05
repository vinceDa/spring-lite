package com.lite.test.common;

import com.lite.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author vince 2024/3/5 17:51
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter DATE_TIME_FORMATTER;

    public StringToLocalDateConverter(DateTimeFormatter DATE_TIME_FORMATTER) {
        this.DATE_TIME_FORMATTER = DATE_TIME_FORMATTER;
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, DATE_TIME_FORMATTER);
    }
}
