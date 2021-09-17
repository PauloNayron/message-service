package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.gateway.ReportGateway;
import org.springframework.stereotype.Service;

@Service
public class ScheduleNewCommunicationUseCase {
    private final ReportGateway reportGateway;

    public ScheduleNewCommunicationUseCase(ReportGateway reportGateway) {
        this.reportGateway = reportGateway;
    }

    public Report execute(Report report) {
        return reportGateway.find(report)
                .orElseGet(() -> reportGateway.save(report));
    }

}
