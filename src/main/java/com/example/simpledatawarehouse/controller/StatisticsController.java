package com.example.simpledatawarehouse.controller;

import com.example.simpledatawarehouse.configuration.SwaggerTags;
import com.example.simpledatawarehouse.controller.request.StatisticsRequest;
import com.example.simpledatawarehouse.controller.response.StatisticsPagedList;
import com.example.simpledatawarehouse.controller.response.StatisticsResponse;
import com.example.simpledatawarehouse.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@Api(tags = {SwaggerTags.STATISTICS})
public class StatisticsController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/findByDateAndParameters")
    @ApiOperation(
            value = "Returns statistics for one day, and additional filters",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<StatisticsResponse>> findByDateAndParameters(
            @ApiParam("Filter by exact day in MM/dd/yyyy format")
            @RequestParam(name = "exactDay", required = false)
            @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate exactDay,

            @ApiParam("Filter by datasource")
            @RequestParam(name = "datasource", required = false) String datasource,

            @ApiParam("Filter by campaign")
            @RequestParam(name = "campaign", required = false) String campaign,

            @ApiParam("Filter by clicks")
            @RequestParam(name = "clicks", required = false) Integer clicks,

            @ApiParam("Filter by impressions")
            @RequestParam(name = "impressions", required = false) Long impressions
    ) {
        StatisticsRequest statisticsRequest = StatisticsRequest.builder()
                .exactDayFilter(exactDay)
                .datasourceFilter(datasource)
                .campaignFilter(campaign)
                .clicksFilter(clicks)
                .impressionsFilter(impressions)
                .build();

        return ResponseEntity.ok(statisticsService.findAll(statisticsRequest));
    }


    @GetMapping("/findByRangeAndDatasource")
    @ApiOperation(
            value = "Returns statistics for a date interval and datasource",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<StatisticsResponse>> findAllByRangeAndDatasource(
            @ApiParam("Filter by date range: [fromDate,toDate]. fromDate in MM/dd/yyyy format")
            @RequestParam(name = "fromDate")
            @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate fromDate,

            @ApiParam("Filter by date range: [fromDate,toDate]. toDate in MM/dd/yyyy format")
            @RequestParam(name = "toDate")
            @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate toDate,

            @ApiParam("Filter by datasource")
            @RequestParam(name = "datasource") String datasource

    ) {
        StatisticsRequest statisticsRequest = StatisticsRequest.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .datasourceFilter(datasource)
                .build();

        return ResponseEntity.ok(statisticsService.findAllByRangeAndDatasource(statisticsRequest));
    }

    @GetMapping("/findAllByDateRange")
    @ApiOperation(
            value = "Returns statistics for a date interval",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<StatisticsResponse>> findAllByDateRange(
            @ApiParam("Filter by date range: [fromDate,toDate]. fromDate in MM/dd/yyyy format")
            @RequestParam(name = "fromDate")
            @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate fromDate,

            @ApiParam("Filter by date range: [fromDate,toDate]. toDate in MM/dd/yyyy format")
            @RequestParam(name = "toDate")
            @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate toDate
    ) {
        StatisticsRequest statisticsRequest = StatisticsRequest.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .build();

        return ResponseEntity.ok(statisticsService.findAllByDateRange(statisticsRequest));
    }

    @GetMapping("/findByParameters")
    @ApiOperation(
            value = "Returns statistics by search parameters",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<StatisticsResponse>> findByParameters(
            @ApiParam("Filter by datasource")
            @RequestParam(name = "datasource", required = false) String datasource,

            @ApiParam("Filter by campaign")
            @RequestParam(name = "campaign", required = false) String campaign,

            @ApiParam("Filter by clicks")
            @RequestParam(name = "clicks", required = false) Integer clicks,

            @ApiParam("Filter by impressions")
            @RequestParam(name = "impressions", required = false) Long impressions
    ) {
        StatisticsRequest statisticsRequest = StatisticsRequest.builder()
                .datasourceFilter(datasource)
                .campaignFilter(campaign)
                .clicksFilter(clicks)
                .impressionsFilter(impressions)
                .build();

        return ResponseEntity.ok(statisticsService.findAll(statisticsRequest));
    }

    @GetMapping("/page")
    @ApiOperation(
            value = "Returns statistics paginated",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<StatisticsPagedList> findByParameterPaginated(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return ResponseEntity.ok(statisticsService.findAll(PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/findAndGroupByDatasource")
    @ApiOperation(
            value = "Returns statistics by search parameters, groups by Datasource",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<Map<String, List<StatisticsResponse>>> findAndGroupByDatasource(
            @ApiParam("Filter by datasource")
            @RequestParam(name = "datasource", required = false) String datasource,

            @ApiParam("Filter by campaign")
            @RequestParam(name = "campaign", required = false) String campaign,

            @ApiParam("Filter by clicks")
            @RequestParam(name = "clicks", required = false) Integer clicks,

            @ApiParam("Filter by impressions")
            @RequestParam(name = "impressions", required = false) Long impressions
    ) {
        StatisticsRequest statisticsRequest = StatisticsRequest.builder()
                .datasourceFilter(datasource)
                .campaignFilter(campaign)
                .clicksFilter(clicks)
                .impressionsFilter(impressions)
                .build();

        return ResponseEntity.ok(statisticsService.findAndGroupByDatasource(statisticsRequest));
    }

    @GetMapping("/findAndGroupByCampaign")
    @ApiOperation(
            value = "Returns statistics by search parameters, groups by Campaign",
            response = StatisticsResponse.class,
            responseContainer = "List"
    )
    public ResponseEntity<Map<String, List<StatisticsResponse>>> findAndGroupByCampaign(
            @ApiParam("Filter by datasource")
            @RequestParam(name = "datasource", required = false) String datasource,

            @ApiParam("Filter by campaign")
            @RequestParam(name = "campaign", required = false) String campaign,

            @ApiParam("Filter by clicks")
            @RequestParam(name = "clicks", required = false) Integer clicks,

            @ApiParam("Filter by impressions")
            @RequestParam(name = "impressions", required = false) Long impressions
    ) {
        StatisticsRequest statisticsRequest = StatisticsRequest.builder()
                .datasourceFilter(datasource)
                .campaignFilter(campaign)
                .clicksFilter(clicks)
                .impressionsFilter(impressions)
                .build();

        return ResponseEntity.ok(statisticsService.findAndGroupByCampaign(statisticsRequest));
    }
}
