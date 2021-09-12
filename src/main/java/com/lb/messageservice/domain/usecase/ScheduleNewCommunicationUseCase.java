package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.app.repository.ScheduledCommunicationRepository;
import com.lb.messageservice.app.repository.dao.CommunicationDAO;
import com.lb.messageservice.domain.entity.Communication;
import org.springframework.stereotype.Service;

@Service
public record ScheduleNewCommunicationUseCase(
        ScheduledCommunicationRepository scheduledCommunicationRepository
) {
    public Communication execute(Communication communication) {
        var dao = CommunicationDAO.fromCommunication(communication);
        scheduledCommunicationRepository.save(dao);
        return dao.toCommunication();
    }
}
