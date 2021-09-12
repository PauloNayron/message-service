package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.app.repository.ScheduledReportRepository;
import com.lb.messageservice.app.repository.dao.ReportDAO;
import com.lb.messageservice.domain.entity.Report;
import org.springframework.stereotype.Service;

@Service
public record ScheduleNewCommunicationUseCase(
        ScheduledReportRepository scheduledReportRepository
) {
    public Report execute(Report report) {
        var dao = ReportDAO.fromReport(report);
        scheduledReportRepository.save(dao);
        return dao.toReport();
    }
}
