package com.lb.messageservice.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.domain.entity.enums.Status;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO extends RepresentationModel<ReportDTO> {
    private Long id;
    @JsonProperty("recipient")
    private @NotBlank Long recipient;
    @JsonProperty("send_date")
    private @NotBlank LocalDateTime sendDate;
    @JsonProperty("message")
    private @NotBlank String message;
    @JsonProperty("channel")
    private @NotBlank ChannelType channel;
    private Status status;
}
