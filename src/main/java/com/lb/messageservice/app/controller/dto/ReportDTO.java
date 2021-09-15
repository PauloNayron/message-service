package com.lb.messageservice.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.Channel;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.entity.channels.EmailChannel;
import com.lb.messageservice.domain.entity.enums.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReportDTO(
        Long recipient,
        @JsonProperty("send_date") LocalDateTime sendDate,
        String message,
        ChannelType channel,
        Status status
) {
    public static ReportDTO fromReport(Report report) {
        return new ReportDTO(
                report.id(),
                report.sendDate(),
                report.message(),
                ChannelType.valueOf(report.channel().name()),
                report.status()
        );
    }

    public Report toReport() {
        return new Report(null, this.message, this.sendDate, this.recipient, this.channel().toClass(), Status.SCHEDULED);
    }
}
