package com.lb.messageservice.app.controller;

import com.lb.messageservice.app.commons.Loggable;
import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.app.service.ChannelMapper;
import com.lb.messageservice.domain.usecase.CancellingReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ConsultationOfReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController implements Loggable {
    private final ChannelMapper channelMapper;
    private final ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase;
    private final ConsultationOfReportSubmissionUseCase consultationOfReportSubmissionUseCase;
    private final CancellingReportSubmissionUseCase cancellingReportSubmissionUseCase;

    @PostMapping
    public ResponseEntity<ReportDTO> postReport(@RequestBody ReportDTO dto) {
        var report = channelMapper.toReport(dto);
        info("dto successfully converted into entity", ReportController.class);

        report = scheduleNewCommunicationUseCase.execute(report);
        info("report scheduled successfully { id:%d, send_date:%s }".formatted(report.id(), report.sendDate()),
                ReportController.class);
        dto = channelMapper.fromReport2Dto(report);
        return ResponseEntity.accepted().body(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Long id) {
        info("retrieving report from id ".concat(id.toString()), ReportController.class);
        var report = consultationOfReportSubmissionUseCase.execute(id);
        info("report retrieved", ReportController.class);
        return ResponseEntity.ok(channelMapper.fromReport2Dto(report));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReportDTO> deleteReportById(@PathVariable Long id) {
        info("Canceling report submission", ReportController.class);
        cancellingReportSubmissionUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
