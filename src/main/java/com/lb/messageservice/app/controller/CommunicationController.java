package com.lb.messageservice.app.controller;

import com.lb.messageservice.app.controller.dto.CommunicationDTO;
import com.lb.messageservice.domain.entity.Communication;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("communication")
public record CommunicationController(
        ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase
) {
    @PostMapping
    public ResponseEntity<CommunicationDTO> postCommunication(@RequestBody @Validated CommunicationDTO dto) {
        Communication communication = null;
        try {
            communication = dto.toCommunication();
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        communication = scheduleNewCommunicationUseCase.execute(communication);
        CommunicationDTO.fromCommunication(communication);
        return ResponseEntity.accepted().body(dto);
    }
}
