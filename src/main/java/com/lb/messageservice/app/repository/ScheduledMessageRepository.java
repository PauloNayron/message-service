package com.lb.messageservice.app.repository;

import com.lb.messageservice.app.repository.dao.ScheduledMessageDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledMessageRepository extends CrudRepository<ScheduledMessageDAO, String> {
}
