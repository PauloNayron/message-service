package com.lb.messageservice.app.repository.dao;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "communication")
public class CommunicationDAO {
    @Id private Long id;
    private String message;
    private LocalDateTime sendDate;
    @Transient private PersonDAO sender;
    @Transient private PersonDAO recipient;
    @CreationTimestamp private LocalDateTime createdAt;
    @UpdateTimestamp private LocalDateTime updatedAt;
}
