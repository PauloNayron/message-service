package com.lb.messageservice.domain.entity;

public record SmsChannel() implements Channel {
    @Override
    public String name() {
        return "SMS";
    }
}
