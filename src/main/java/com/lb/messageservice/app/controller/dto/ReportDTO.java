package com.lb.messageservice.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.enums.Status;

import java.time.LocalDateTime;

public record ReportDTO(
        Long recipient,
        @JsonProperty("send_date") LocalDateTime sendDate,
        String message,
        ChannelType channel,
        Status status
) { }
