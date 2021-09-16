package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.gateway.ReportGateway;
import mocks.ReportMocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ScheduleNewCommunicationUseCaseTest {
    ScheduleNewCommunicationUseCase useCase;

    @MockBean
    private ReportGateway reportGateway;

    @BeforeEach
    public void setUp() {
        this.useCase = new ScheduleNewCommunicationUseCase(reportGateway);
    }

    @Test
    @DisplayName("given an existing report, you should not save a new one")
    public void reportExist() {
        // given
        var report = ReportMocked.reportScheduled;
        Mockito.when(reportGateway.find(Mockito.any()))
                .thenReturn(Optional.of(report));
        // exec
        var result = useCase.execute(Mockito.any());
        // should
        verify(reportGateway, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("given a non-existent report, a new report must be saved")
    public void reportNotExist() {
        // given
        Mockito.when(reportGateway.find(Mockito.any()))
                .thenReturn(Optional.empty());
        // exec
        var result = useCase.execute(Mockito.any());
        // should
        verify(reportGateway, Mockito.times(1)).save(Mockito.any());
    }
}