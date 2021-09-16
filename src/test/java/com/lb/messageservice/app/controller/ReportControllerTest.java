package com.lb.messageservice.app.controller;

import com.lb.messageservice.domain.usecase.CancellingReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ConsultationOfReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class ReportControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase;
    @MockBean
    ConsultationOfReportSubmissionUseCase consultationOfReportSubmissionUseCase;
    @MockBean
    CancellingReportSubmissionUseCase cancellingReportSubmissionUseCase;

    @Test
    @DisplayName("teste")
    public void createReportSuccess() throws Exception {

        var json = """
                {
                    "recipient": 1,
                    "send_date": "2022-02-01T00:00:00",
                    "message": "Feliz Ano Novo!",
                    "channel": "SMS"
                }
                """;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/report")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc
                .perform(request)
                .andExpect(status().is2xxSuccessful());
    }

}