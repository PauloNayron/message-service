package com.lb.messageservice.app.gateway;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.app.repository.ReportRepository;
import com.lb.messageservice.app.repository.dao.ReportDAO;
import com.lb.messageservice.app.service.ChannelMapper;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.gateway.ReportGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record ReportGatewayImpl(
        ChannelMapper channelMapper,
        ReportRepository repository
) implements ReportGateway, Loggable {
    @Override
    public Report save(Report report) {
        var dao = channelMapper.fromReport2Dao(report);
        return channelMapper.toReport(repository.save(dao));
    }

    @Override
    public Optional<Report> find(Report report) {
        var dao = channelMapper.fromReport2Dao(report);
        var op =
                repository.findReport(
                        dao.getChannel().ordinal(),
                        dao.getMessage(),
                        dao.getRecipient(),
                        dao.getSendDate()
                );
        op.ifPresent(reportDAO -> info("report found with id:".concat(op.get().getId().toString()),
                ReportGatewayImpl.class));
        return op.map(channelMapper::toReport);
    }

    @Override
    public Optional<Report> findById(Long reportId) {
        return repository.findById(reportId).map(channelMapper::toReport);
    }
}
