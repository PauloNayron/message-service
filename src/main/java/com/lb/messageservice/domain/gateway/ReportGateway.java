package com.lb.messageservice.domain.gateway;

import com.lb.messageservice.domain.entity.Report;

import java.util.Optional;

public interface ReportGateway {
    Report save(Report report);

    Optional<Report> find(Report report);
}
