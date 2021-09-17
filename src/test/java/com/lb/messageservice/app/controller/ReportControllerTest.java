package com.lb.messageservice.app.controller;

import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.app.service.ChannelMapper;
import com.lb.messageservice.domain.usecase.CancellingReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ConsultationOfReportSubmissionUseCase;
import com.lb.messageservice.domain.usecase.ScheduleNewCommunicationUseCase;
import mocks.ReportMocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
    private ChannelMapper channelMapper;
    @MockBean
    private ScheduleNewCommunicationUseCase scheduleNewCommunicationUseCase;
    @MockBean
    private ConsultationOfReportSubmissionUseCase consultationOfReportSubmissionUseCase;
    @MockBean
    private CancellingReportSubmissionUseCase cancellingReportSubmissionUseCase;

    @Test
    @DisplayName("given a valid request body, it should return status 2xx")
    public void createReportSuccess() throws Exception {
        // given
        var reportScheduled = ReportMocked.reportScheduled;
        var dto = ReportMocked.reportDtoScheduled;

        Mockito.when(channelMapper.toReport(Mockito.any(ReportDTO.class)))
                .thenReturn(reportScheduled);
        Mockito.when(scheduleNewCommunicationUseCase.execute(reportScheduled))
                .thenReturn(reportScheduled);
        Mockito.when(channelMapper.fromReport2Dto(reportScheduled))
                .thenReturn(dto);
        // exec
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

    @Test
    @DisplayName("given a valid path variable, it should return status 2xx")
    public void getReportSuccess() throws Exception {
        // given
        var reportScheduled = ReportMocked.reportScheduled;
        var dto = ReportMocked.reportDtoScheduled;
        Mockito.when(consultationOfReportSubmissionUseCase.execute(1l))
                .thenReturn(reportScheduled);
        Mockito.when(channelMapper.fromReport2Dto(reportScheduled))
                .thenReturn(dto);
        // exec
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/report/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("given a valid path variable to be deleted, it should return status 2xx")
    public void deleteReportSuccess() throws Exception {
        // exec
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/report/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().is2xxSuccessful());
    }

}