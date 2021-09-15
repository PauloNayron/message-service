package com.lb.messageservice.domain.entity.enums;

import java.util.Arrays;
import java.util.Collection;

public enum Status {
    SCHEDULED,
    IN_PROCESS,
    PUSHED,
    CANCELED,
    ERROR;

    public Boolean validateNewStatus(Status newStatus) {
        if (this.equals(SCHEDULED)) return true;
        else return this.equals(IN_PROCESS) && this.inProccessNextStatus().contains(newStatus);
    }

    private Collection<Status> inProccessNextStatus() {
        return Arrays.asList(PUSHED, ERROR);
    }
}
