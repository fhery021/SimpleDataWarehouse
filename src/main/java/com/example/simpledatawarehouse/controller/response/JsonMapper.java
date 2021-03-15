package com.example.simpledatawarehouse.controller.response;

import com.example.simpledatawarehouse.controller.request.Fields;
import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JsonMapper {

    public List<String> mapToResponse(MetricsRequest metricsRequest, List<MetricsResponse> metricsResponses) {
//        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(
//                excludedFields(metricsRequest));
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("PropertyFilter", MetricsPropertyFilter.getFilter(metricsRequest.getShowOnlyFields()));


//        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
//        MyDto dtoObject = new MyDto();
//        dtoObject.setIntValue(-1);
//
        // Group and filter
        // no grouping
        List<String> response = new ArrayList<>();
        if (metricsRequest.getGroupBy() == null) {
            metricsResponses.forEach(resp -> {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    response.add(mapper.writer(filterProvider).writeValueAsString(resp));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }



//        MappingJacksonValue mappingJacksonValue;
//
//        if (metricsRequest.getGroupBy() != null) {
//            GroupMetricsResponse groupMetricsResponse = new GroupMetricsResponse();
//            Map<Object, List<MetricsResponse>> groupedMetrics = groupMetricsResponse.group(metricsResponses, metricsRequest.getGroupBy());
//            if (groupedMetrics != null) {
//                mappingJacksonValue = new MappingJacksonValue(groupedMetrics);
//            } else {
//                mappingJacksonValue = new MappingJacksonValue(metricsResponses);
//            }
//        } else {
//            mappingJacksonValue = new MappingJacksonValue(metricsResponses);
//        }
//
//        mappingJacksonValue.setFilters(filterProvider);
//        mapper.addMixIn(Object.class, Profile.class);
//        mappingJacksonValue.add

        return response;
    }

    private Set<String> excludedFields(MetricsRequest metricsRequest) {
        Set<String> excludedFields = new HashSet<>();
        if (metricsRequest.getShowOnlyFields() != null){
            Arrays.stream(Fields.AVAILABLE_FIELDS)
                    .filter(field -> !metricsRequest.getShowOnlyFields().contains(field))
                    .forEach(excludedFields::add);
        }
        return excludedFields;
    }
}
