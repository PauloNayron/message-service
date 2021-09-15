package com.lb.messageservice.app.service;

import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.app.repository.dao.ReportDAO;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.entity.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChannelMapper {
    private final ChannelFactory channelFactory;

    public Report toReport(ReportDTO dto) {
        return new Report(null,
                dto.message(),
                dto.sendDate(),
                dto.recipient(),
                channelFactory.findByChannel(dto.channel()),
                Status.SCHEDULED);
    }

    public ReportDTO fromReport2Dto(Report report) {
        return new ReportDTO(
                report.id(),
                report.sendDate(),
                report.message(),
                ChannelType.valueOf(report.channel().name()),
                report.status()
        );
    }

    public ReportDAO fromReport2Dao(Report report) {
        return ReportDAO.builder()
                    .id(report.id())
                    .message(report.message())
                    .sendDate(report.sendDate())
                    .recipient(report.recipient())
                    .channel(ChannelType.valueOf(report.channel().name()))
                    .status(report.status())
                    .build();
    }

    public Report toReport(ReportDAO dto) {
        return new Report(
                dto.getId(),
                dto.getMessage(),
                dto.getSendDate(),
                dto.getRecipient(),
                channelFactory.findByChannel(dto.getChannel()),
                dto.getStatus()
        );
    }
}
