package com.lb.messageservice.domain.entity.channels;

import com.lb.messageservice.domain.entity.Channel;

public record PushChannel() implements Channel {
    @Override
    public String name() {
        return "PUSH";
    }
}
