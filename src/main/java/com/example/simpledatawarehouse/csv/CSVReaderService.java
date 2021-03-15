package com.example.simpledatawarehouse.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class CSVReaderService {

    @Value("classpath:statistics.csv")
    private Resource res;

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public List<Statistics> processCSV() throws IOException {

        InputStream inputStream = res.getInputStream();

        File file = new File("csv");
        copyInputStreamToFile(inputStream, file);


        List<Statistics> statistics = new CsvToBeanBuilder(new FileReader(file))
                .withType(Statistics.class)
                .build()
                .parse();

        return statistics;
    }

    // FileReader does not work under docker
    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }
}
