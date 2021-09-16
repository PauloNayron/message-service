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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ConsultationOfReportSubmissionUseCaseTest {
    ConsultationOfReportSubmissionUseCase useCase;

    @MockBean
    private ReportGateway reportGateway;

    @BeforeEach
    public void setUp() {
        this.useCase = new ConsultationOfReportSubmissionUseCase(reportGateway);
    }

    @Test
    @DisplayName("given an existing report ID, it should return a report")
    public void reportExist() {
        // given
        var report = ReportMocked.reportScheduled;
        Mockito.when(reportGateway.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(report));
        // exec
        var result = useCase.execute(Mockito.anyLong());
        // should
        assertThat(result.id()).isEqualTo(1L);
    }

    @Test
    @DisplayName("given a report ID that does not exist, an exception must be thrown.")
    public void reportDoesNotExist() {
        // given
        Mockito.when(reportGateway.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        // exec
        // should
        assertThrows(ReportNotFoundException.class, () -> useCase.execute(Mockito.anyLong()));
    }
}