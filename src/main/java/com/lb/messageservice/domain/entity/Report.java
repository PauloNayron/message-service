package com.lb.messageservice.domain.entity;

import java.time.LocalDateTime;

public record Report(
        Long id,
        String message,
        LocalDateTime sendDate,
        Long recipient,
        Channel channel
) {}
