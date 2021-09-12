package com.lb.messageservice.app.repository;

import com.lb.messageservice.app.repository.dao.CommunicationDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledMessageRepository extends CrudRepository<CommunicationDAO, String> {
}
