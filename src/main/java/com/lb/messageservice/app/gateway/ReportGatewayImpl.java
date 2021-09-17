package com.lb.messageservice.app.gateway;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.app.repository.ReportRepository;
import com.lb.messageservice.app.service.ChannelMapper;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.gateway.ReportGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class ReportGatewayImpl implements ReportGateway, Loggable {
    private final ChannelMapper channelMapper;
    private final ReportRepository repository;

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
