package com.example.simpledatawarehouse.csv;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class LocalDateConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/d/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("M/dd/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))

                .appendOptional(DateTimeFormatter.ofPattern("M/d/yy"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/d/yy"))
                .appendOptional(DateTimeFormatter.ofPattern("M/dd/yy"))
                .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yy"))

                .toFormatter();
        return LocalDate.parse(value, formatter);
    }

}
