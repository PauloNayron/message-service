package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.exception.ReportNotFoundException;
import com.lb.messageservice.domain.gateway.ReportGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationOfReportSubmissionUseCase {
    private ReportGateway reportGateway;

    public ConsultationOfReportSubmissionUseCase(ReportGateway reportGateway) {
        this.reportGateway = reportGateway;
    }

    public Report execute(Long reportId) {
        Optional<Report> op = reportGateway.findById(reportId);
        if (op.isPresent()) return op.get();
        else throw new ReportNotFoundException("report does not exist");
    }
}
