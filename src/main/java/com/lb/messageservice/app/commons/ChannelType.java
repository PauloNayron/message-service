package com.lb.messageservice.app.commons;

import com.lb.messageservice.domain.entity.*;
import com.lb.messageservice.domain.entity.channels.EmailChannel;
import com.lb.messageservice.domain.entity.channels.PushChannel;
import com.lb.messageservice.domain.entity.channels.SmsChannel;
import com.lb.messageservice.domain.entity.channels.WhatsappChannel;
import com.lb.messageservice.domain.exception.UnknownChannelException;

public enum ChannelType {
    EMAIL,
    SMS,
    PUSH,
    WHATSAPP;

    public Channel toClass() {
        if (this.equals(EMAIL)) return new EmailChannel();
        else if (this.equals(SMS)) return new SmsChannel();
        else if (this.equals(PUSH)) return new PushChannel();
        else if (this.equals(WHATSAPP)) return new WhatsappChannel();
        else throw new UnknownChannelException("Unknown".concat(this.name()).concat("Channel"));
    }
}
