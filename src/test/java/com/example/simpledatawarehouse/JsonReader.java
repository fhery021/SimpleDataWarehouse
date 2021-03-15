package com.example.simpledatawarehouse;

import org.h2.util.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class JsonReader {

    public static String readJson(String resourcePath) {
        try {
            return IOUtils.readStringAndClose(new InputStreamReader(JsonReader.class.getResourceAsStream(resourcePath)), 0);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private JsonReader() {}
}
