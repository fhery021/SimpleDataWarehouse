package com.example.simpledatawarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple backend application that exposes data - extracted from a csv file- via an API.
 */
@SpringBootApplication
public class SimpleDataWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleDataWarehouseApplication.class, args);
    }
}
