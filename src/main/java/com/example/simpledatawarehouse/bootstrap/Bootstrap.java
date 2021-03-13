package com.example.simpledatawarehouse.bootstrap;

import com.example.simpledatawarehouse.csv.LoadDataFromCSVService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final LoadDataFromCSVService loadDataFromCSVService;

    public Bootstrap(LoadDataFromCSVService loadDataFromCSVService) {
        this.loadDataFromCSVService = loadDataFromCSVService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadDataFromCSVService.load();
    }
}
