package com.lb.messageservice.domain.entity;

public record WhatsappChannel() implements Channel {
    @Override
    public String name() {
        return "WHATSAPP";
    }
}
