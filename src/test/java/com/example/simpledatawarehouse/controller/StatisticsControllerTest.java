package com.example.simpledatawarehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void findByDateAndParameters() throws Exception {
        mockMvc.perform(get("/api/statistics/findByDateAndParameters?datasource=Google Ads&exactDay=11/12/2019"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }

    @Test
    void findByRangeAndDatasourceNoDatasource() throws Exception {
        mockMvc.perform(get("/api/statistics/findByRangeAndDatasource/?fromDate=11/12/2019&toDate=11/13/2019"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findByRangeAndDatasource() throws Exception {
        mockMvc.perform(get("/api/statistics/findByRangeAndDatasource/?fromDate=11/12/2019&toDate=11/13/2019&datasource=Google Ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }

    @Test
    void findAllByDateRangeNoRange() throws Exception {
        mockMvc.perform(get("/api/statistics/findAllByDateRange"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAllByDateRange() throws Exception {
        mockMvc.perform(get("/api/statistics/findAllByDateRange?fromDate=11/11/2019&toDate=11/12/2019"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }

    @Test
    void findByParameters() throws Exception {
        mockMvc.perform(get("/api/statistics/findByParameters?datasource=Google Ads&campaign=Adventmarkt Touristik&clicks=7&impressions=14508"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(1)));
    }

    @Test
    void page() throws Exception {
        mockMvc.perform(get("/api/statistics/page?pageNumber=2&pageSize=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }

    @Test
    void findAndGroupByDatasource() throws Exception {
        mockMvc.perform(get("/api/statistics/findAndGroupByDatasource?datasource=Google Ads&campaign=Adventmarkt Touristik&clicks=7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(1)));
    }

    @Test
    void findAndGroupByCampaign() throws Exception {
        mockMvc.perform(get("/api/statistics/findAndGroupByCampaign?datasource=Google Ads&clicks=100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }
}
