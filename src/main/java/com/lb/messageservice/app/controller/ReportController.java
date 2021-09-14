package com.lb.messageservice.app.controller;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.usecase.ConsultationOfReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("report")
public record ReportController(
        ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase,
        ConsultationOfReportSubmissionUseCase consultationOfReportSubmissionUseCase
) implements Loggable {

    @PostMapping
    public ResponseEntity<ReportDTO> postReport(@RequestBody ReportDTO dto) {
        Report report = null;
        try {
            report = dto.toCommunication();
            info("dto successfully converted into entity", ReportController.class);
        } catch (Exception e) {
            error(e.getMessage(), ReportController.class);
            ResponseEntity.badRequest().body(e.getMessage());
        }
        report = scheduleNewCommunicationUseCase.execute(report);
        info("report scheduled successfully { id:%d, send_date:%s }".formatted(report.id(), report.sendDate()),
                ReportController.class);
        ReportDTO.fromCommunication(report);
        return ResponseEntity.accepted().body(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Long id) {
        info("retrieving report from id ".concat(id.toString()), ReportController.class);
        try {
            var report = consultationOfReportSubmissionUseCase.execute(id);
            info("report retrieved", ReportController.class);
            return ResponseEntity.ok(ReportDTO.fromCommunication(report));
        } catch (Exception e) {
            info("report id ".concat(id.toString()).concat(" does not exist"), ReportController.class);
            return ResponseEntity.noContent().build();
        }
    }
}
