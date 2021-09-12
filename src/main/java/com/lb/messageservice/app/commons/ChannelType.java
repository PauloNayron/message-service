package com.lb.messageservice.app.commons;

import com.lb.messageservice.domain.entity.*;

public enum ChannelType {
    EMAIL,
    SMS,
    PUSH,
    WHATSAPP;

    public Channel toClass() throws Exception {
        if (this.equals(EMAIL)) return new EmailChannel();
        else if (this.equals(SMS)) return new SmsChannel();
        else if (this.equals(PUSH)) return new PushChannel();
        else if (this.equals(WHATSAPP)) return new WhatsappChannel();
        else throw new Exception("Unknown Channel.");
    }
}
