package com.example.simpledatawarehouse.csv;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CSVReaderTest {

    @Spy
    private final CSVReaderService csvReaderService = new CSVReaderService();

    @Before
    public void setUp() throws MalformedURLException {
        Resource resource = new FileUrlResource("src/main/resources/statistics.csv");
        ReflectionTestUtils.setField(csvReaderService, "res", resource);
    }

    @Test
    public void readStatistics() throws IOException {
        List<Statistics> statisticsList = csvReaderService.processCSV();
        assertNotNull(statisticsList);
        assertTrue(statisticsList.size() > 0);
    }
}
