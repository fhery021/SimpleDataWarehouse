package com.example.simpledatawarehouse.controller.response;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class StatisticsPagedList  extends PageImpl<StatisticsResponse> implements Serializable {

    public StatisticsPagedList(List<StatisticsResponse> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public StatisticsPagedList(List<StatisticsResponse> content) {
        super(content);
    }
}
