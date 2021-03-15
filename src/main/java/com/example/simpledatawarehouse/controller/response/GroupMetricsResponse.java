package com.example.simpledatawarehouse.controller.response;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class GroupMetricsResponse {

    public static final String[] AVAILABLE_GROUP_BY = {"datasource", "campaign", "date"};

    public Map<Object, List<MetricsResponse>> group(List<MetricsResponse> metricsResponses, String groupByDimensions) {
        if (Arrays.stream(AVAILABLE_GROUP_BY).noneMatch(available -> available.equals(groupByDimensions))) {
            return null;
        }
        switch (groupByDimensions) {
            case "datasource":
                return metricsResponses.stream().collect(groupingBy(MetricsResponse::getDataSource));
            case "campaign":
                return metricsResponses.stream().collect(groupingBy(MetricsResponse::getCampaign));
            case "date":
                return metricsResponses.stream().collect(groupingBy(MetricsResponse::getDaily));
        }
        return null;
    }

}