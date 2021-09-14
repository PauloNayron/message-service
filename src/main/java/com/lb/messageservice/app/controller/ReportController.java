package com.lb.messageservice.app.controller;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("report")
public record ReportController(
        ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase
) implements Loggable {

    @PostMapping
    public ResponseEntity<ReportDTO> postReport(@RequestBody @Validated ReportDTO dto) {
        Report report = null;
        try {
            info("convert dto into entity", ReportController.class);
            report = dto.toCommunication();
        } catch (Exception e) {
            error(e.getMessage(), ReportController.class);
            ResponseEntity.badRequest().body(e.getMessage());
        }
        report = scheduleNewCommunicationUseCase.execute(report);
        ReportDTO.fromCommunication(report);
        return ResponseEntity.accepted().body(dto);
    }
}
