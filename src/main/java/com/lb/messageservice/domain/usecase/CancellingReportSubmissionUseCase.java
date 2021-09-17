package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.domain.entity.enums.Status;
import com.lb.messageservice.domain.exception.ReportNotFoundException;
import com.lb.messageservice.domain.gateway.ReportGateway;
import org.springframework.stereotype.Service;

@Service
public class CancellingReportSubmissionUseCase {
    private final ReportGateway reportGateway;

    public CancellingReportSubmissionUseCase(ReportGateway reportGateway) {
        this.reportGateway = reportGateway;
    }

    public void execute(Long reportId) {
        var report = reportGateway.findById(reportId);
        if (report.isPresent()) {
            var updatedStatus = report.get().updateStatus(Status.CANCELED);
            reportGateway.save(updatedStatus);
        } else throw new ReportNotFoundException("report does not exist");
    }
}
