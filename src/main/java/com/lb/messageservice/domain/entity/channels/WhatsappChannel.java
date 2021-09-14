package com.lb.messageservice.domain.entity.channels;

import com.lb.messageservice.domain.entity.Channel;

public record WhatsappChannel() implements Channel {
    @Override
    public String name() {
        return "WHATSAPP";
    }
}
