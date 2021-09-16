package com.lb.messageservice.domain.usecase;

import com.lb.messageservice.domain.exception.ReportNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class CancellingReportSubmissionUseCaseTest {
    CancellingReportSubmissionUseCase useCase;

    @MockBean
    private ReportGateway reportGateway;

    @BeforeEach
    public void setUp() {
        this.useCase = new CancellingReportSubmissionUseCase(reportGateway);
    }

    @Test
    @DisplayName("given an Id of an existing report, it should change the report status to CANCELED")
    public void cancelReportWithSuccess() {
        // given
        var report = ReportMocked.reportScheduled;

        Mockito.when(reportGateway.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(report));
        Mockito.when(reportGateway.save(Mockito.any()))
                .thenReturn(report);
        // exec
        useCase.execute(1L);
        // should
        verify(reportGateway, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("given a report ID that does not exist, an exception must be thrown.")
    public void cancelReportNotFound() {
        // given
        Mockito.when(reportGateway.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        // exec
        assertThrows(ReportNotFoundException.class, () -> useCase.execute(1L));
        // should
        verify(reportGateway, Mockito.never()).save(Mockito.any());
    }
}