package com.example.simpledatawarehouse.csv;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class LocalDateConverterTest {

    @Test
    public void month1Days1Years4FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("3/1/2021");

        LocalDate expectedDate = LocalDate.of(2021, 3, 1);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month2Days1Years4FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("12/1/2021");

        LocalDate expectedDate = LocalDate.of(2021, 12, 1);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month1Days2Years4FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("3/31/2021");

        LocalDate expectedDate = LocalDate.of(2021, 3, 31);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month2Days2Years4FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("10/22/2021");

        LocalDate expectedDate = LocalDate.of(2021, 10, 22);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month1Days1Years2FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("3/1/21");

        LocalDate expectedDate = LocalDate.of(2021, 3, 1);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month2Days1Years2FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("11/1/21");

        LocalDate expectedDate = LocalDate.of(2021, 11, 1);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month1Days2Years2FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("3/21/21");

        LocalDate expectedDate = LocalDate.of(2021, 3, 21);

        assertEquals(expectedDate, converted);
    }

    @Test
    public void month2Days2Years2FormatTest() {
        LocalDateConverter localDateConverter = new LocalDateConverter();
        LocalDate converted = (LocalDate) localDateConverter.convert("12/15/21");

        LocalDate expectedDate = LocalDate.of(2021, 12, 15);

        assertEquals(expectedDate, converted);
    }

}
