package com.lb.messageservice.domain.entity;

public record EmailChannel() implements Channel {
    @Override
    public String name() {
        return "EMAIL";
    }
}
