package com.lb.messageservice.app.gateway;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.app.repository.ReportRepository;
import com.lb.messageservice.app.repository.dao.ReportDAO;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.gateway.ReportGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record ReportGatewayImpl(
        ReportRepository repository
) implements ReportGateway, Loggable {
    @Override
    public Report save(Report report) {
        var dao = ReportDAO.fromReport(report);
        return repository.save(dao).toReport();
    }

    @Override
    public Optional<Report> find(Report report) {
        var dao = ReportDAO.fromReport(report);
        var op =
                repository.findReport(
                        dao.getChannel().ordinal(),
                        dao.getMessage(),
                        dao.getRecipient(),
                        dao.getSendDate()
                );
        op.ifPresent(reportDAO -> info("report found with id:".concat(op.get().getId().toString()),
                ReportGatewayImpl.class));
        return op.map(ReportDAO::toReport);
    }
}
