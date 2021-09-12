package com.lb.messageservice.domain.entity;

import java.time.LocalDateTime;

public record Communication(
        Long id,
        String message,
        LocalDateTime sendDate,
        Person recipient,
        CommunicationChannel communicationChannel
) {}
