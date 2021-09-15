package com.lb.messageservice.app.service;

import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.Channel;
import com.lb.messageservice.domain.entity.channels.EmailChannel;
import com.lb.messageservice.domain.entity.channels.PushChannel;
import com.lb.messageservice.domain.entity.channels.SmsChannel;
import com.lb.messageservice.domain.entity.channels.WhatsappChannel;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

import static com.lb.messageservice.app.commons.ChannelType.*;

@Component
public class ChannelFactory {
    private final EnumMap<ChannelType, Channel> mapChannel = new EnumMap<>(ChannelType.class);

    public ChannelFactory() {
        mapChannel.put(EMAIL, new EmailChannel());
        mapChannel.put(SMS, new SmsChannel());
        mapChannel.put(PUSH, new PushChannel());
        mapChannel.put(WHATSAPP, new WhatsappChannel());
    }

    public Channel findByChannel(ChannelType channelType) {
        return mapChannel.get(channelType);
    }
}
