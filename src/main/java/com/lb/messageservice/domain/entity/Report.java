package com.lb.messageservice.domain.entity;

import com.lb.messageservice.domain.entity.enums.Status;
import com.lb.messageservice.domain.exception.StatusCannotBeUpdateException;

import java.time.LocalDateTime;

public record Report(
        Long id,
        String message,
        LocalDateTime sendDate,
        Long recipient,
        Channel channel,
        Status status
) {
    public Report updateStatus(Status newStatus) {
        if (this.status.validateNewStatus(newStatus)) {
            return new Report(id, message, sendDate, recipient, channel, newStatus);
        } else throw new StatusCannotBeUpdateException("status cannot be updated");
    }
}
