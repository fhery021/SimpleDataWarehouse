package com.example.simpledatawarehouse.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CSVReaderService {

    @Value("classpath:reddit-jokes.csv")
    private Resource res;

    public List<Statistics> processCSV() throws IOException {

        List<Statistics> statistics = new CsvToBeanBuilder(new FileReader(res.getFile()))
                .withType(Statistics.class)
                .build()
                .parse();

        return statistics;
    }
}
