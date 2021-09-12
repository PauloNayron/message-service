package com.lb.messageservice.app.repository;

import com.lb.messageservice.app.repository.dao.ReportDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledReportRepository extends CrudRepository<ReportDAO, String> {
}
