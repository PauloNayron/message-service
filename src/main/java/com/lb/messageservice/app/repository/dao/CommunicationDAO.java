package com.lb.messageservice.app.repository.dao;

import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.Communication;
import com.lb.messageservice.domain.entity.EmailChannel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "communication")
public class CommunicationDAO {
    @Id private Long id;
    private String message;
    private LocalDateTime sendDate;
    private Long recipient;
    private ChannelType channel;
    @CreationTimestamp private LocalDateTime createdAt;
    @UpdateTimestamp private LocalDateTime updatedAt;

    static public CommunicationDAO fromCommunication(Communication communication) {
        return CommunicationDAO.builder()
                .id(communication.id())
                .message(communication.message())
                .sendDate(communication.sendDate())
                .recipient(communication.recipient())
                .channel(ChannelType.valueOf(communication.channel().name()))
                .build();
    }

    public Communication toCommunication() {
        return new Communication(this.id, this.message, this.sendDate, this.recipient, new EmailChannel());
    }
}
