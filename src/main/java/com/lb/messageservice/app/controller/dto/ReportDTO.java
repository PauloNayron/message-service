package com.lb.messageservice.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.enums.Status;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record ReportDTO(
        @NotBlank @JsonProperty("recipient") Long recipient,
        @NotBlank @JsonProperty("send_date") LocalDateTime sendDate,
        @NotBlank @JsonProperty("message") String message,
        @NotBlank @JsonProperty("channel") ChannelType channel,
        Status status
) { }
