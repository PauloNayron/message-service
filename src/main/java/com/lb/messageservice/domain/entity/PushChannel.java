package com.lb.messageservice.domain.entity;

public record PushChannel() implements Channel {
    @Override
    public String name() {
        return "PUSH";
    }
}
