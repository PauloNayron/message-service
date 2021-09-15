package com.lb.messageservice.app.repository;

import com.lb.messageservice.app.repository.dao.ReportDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<ReportDAO, String> {
    @Query(value = "select * from report r where channel = :channel and message = :message and recipient = :recipient and send_date = :sendDate and status <> 3", nativeQuery = true)
    Optional<ReportDAO> findReport(
            @Param("channel") Integer channel,
            @Param("message") String message,
            @Param("recipient") Long recipient,
            @Param("sendDate") LocalDateTime send_date);

    Optional<ReportDAO> findById(Long reportId);
}
