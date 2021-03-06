package com.lb.messageservice.app.repository.dao;

import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
public class ReportDAO {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String message;
    private LocalDateTime sendDate;
    private Long recipient;
    private ChannelType channel;
    private Status status;
    @CreationTimestamp private LocalDateTime createdAt;
    @UpdateTimestamp private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDAO that = (ReportDAO) o;
        return message.equals(that.message) && sendDate.equals(that.sendDate) && recipient.equals(that.recipient) && channel == that.channel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sendDate, recipient, channel);
    }
}
