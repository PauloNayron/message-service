package com.lb.messageservice.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.Report;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReportDTO(
        Long recipient,
        @JsonProperty("send_date") LocalDateTime sendDate,
        String message,
        ChannelType channel
) {
    public static ReportDTO fromCommunication(Report report) {
        return new ReportDTO(
                report.id(),
                report.sendDate(),
                report.message(),
                ChannelType.valueOf(report.channel().name())
        );
    }

    public Report toCommunication() throws Exception {
        return new Report(null, this.message, this.sendDate, this.recipient, channel.toClass());
    }
}
