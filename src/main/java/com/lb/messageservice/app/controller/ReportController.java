package com.lb.messageservice.app.controller;

import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("communication")
public record ReportController(
        ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase
) {
    @PostMapping
    public ResponseEntity<ReportDTO> postCommunication(@RequestBody @Validated ReportDTO dto) {
        Report report = null;
        try {
            report = dto.toCommunication();
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        report = scheduleNewCommunicationUseCase.execute(report);
        ReportDTO.fromCommunication(report);
        return ResponseEntity.accepted().body(dto);
    }
}
