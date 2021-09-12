package com.lb.messageservice.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.Communication;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommunicationDTO(
        Long recipient,
        @JsonProperty("send_date") LocalDateTime sendDate,
        String message,
        ChannelType channel
) {
    public static CommunicationDTO fromCommunication(Communication communication) {
        return new CommunicationDTO(
                communication.id(),
                communication.sendDate(),
                communication.message(),
                ChannelType.valueOf(communication.channel().name())
        );
    }

    public Communication toCommunication() throws Exception {
        return new Communication(1L, this.message, this.sendDate, this.recipient, channel.toClass());
    }
}
