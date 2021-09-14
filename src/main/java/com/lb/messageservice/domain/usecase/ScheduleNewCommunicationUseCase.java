package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.gateway.ReportGateway;
import org.springframework.stereotype.Service;

@Service
public record ScheduleNewCommunicationUseCase(
        ReportGateway reportGateway
) implements Loggable {
    public Report execute(Report report) {
        return reportGateway.find(report)
                .orElseGet(() -> reportGateway.save(report));
    }
}
