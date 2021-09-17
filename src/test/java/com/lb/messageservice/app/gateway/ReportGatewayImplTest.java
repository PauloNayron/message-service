package com.lb.messageservice.app.gateway;

import com.lb.messageservice.app.repository.ReportRepository;
import com.lb.messageservice.app.service.ChannelMapper;
import com.lb.messageservice.domain.entity.Report;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ReportGatewayImplTest {

    ReportGatewayImpl reportGateway;

    @MockBean
    ChannelMapper channelMapper;
    @MockBean
    ReportRepository repository;

    @BeforeEach
    public void setUp() {
        this.reportGateway = new ReportGatewayImpl(channelMapper, repository);
    }

    @Test
    @DisplayName("given a valid report, it should return a report")
    public void saveReportSuccess() {
        // given
        var report = ReportMocked.reportScheduled;
        var dao = ReportMocked.reportDAOScheduled;
        Mockito.when(channelMapper.fromReport2Dao(report))
                        .thenReturn(dao);
        Mockito.when(repository.save(dao))
                .thenReturn(dao);
        Mockito.when(channelMapper.toReport(dao))
                .thenReturn(report);
        // exec
        Report reportSaved = reportGateway.save(report);
        // should
        assertThat(report).isEqualTo(reportSaved);

        verify(channelMapper, Mockito.times(1)).fromReport2Dao(report);
        verify(repository, Mockito.times(1)).save(dao);
        verify(channelMapper, Mockito.times(1)).toReport(dao);
    }

    @Test
    @DisplayName("given a valid report, it should find a report")
    public void findReportSuccess() {
        // given
        var report = ReportMocked.reportScheduled;
        var dao = ReportMocked.reportDAOScheduled;
        Mockito.when(channelMapper.fromReport2Dao(report))
                .thenReturn(dao);
        Mockito.when(
                repository.findReport(
                        dao.getChannel().ordinal(),
                        dao.getMessage(),
                        dao.getRecipient(),
                        dao.getSendDate()
                )).thenReturn(Optional.of(dao));
        Mockito.when(channelMapper.toReport(dao))
                .thenReturn(report);
        // exec
        var result = reportGateway.find(report);
        // should
        assertThat(Optional.of(report)).isEqualTo(result);

        verify(channelMapper, Mockito.times(1)).fromReport2Dao(report);
        verify(repository, Mockito.times(1)).findReport(anyInt(), anyString(), anyLong(), any());
        verify(channelMapper, Mockito.times(1)).toReport(dao);
    }

    @Test
    @DisplayName("given an existing report ID, it should find a report")
    public void findReportByIdSuccess() {
        // given
        var dao = ReportMocked.reportDAOScheduled;
        var report = ReportMocked.reportScheduled;
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(dao));
        Mockito.when(channelMapper.toReport(dao)).thenReturn(report);
        // exec
        var result = reportGateway.findById(1L);
        // should
        assertThat(Optional.of(report)).isEqualTo(result);

        verify(repository, Mockito.times(1)).findById(1L);
        verify(channelMapper, Mockito.times(1)).toReport(dao);
    }

    @Test
    @DisplayName("given a non-existent report id, it should opt for empty")
    public void findReportByIdNotFound() {
        // given
        var dao = ReportMocked.reportDAOScheduled;
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(dao));
        // exec
        var result = reportGateway.findById(1L);
        // should
        assertThat(Optional.empty()).isEqualTo(result);

        verify(repository, Mockito.times(1)).findById(1L);
    }
}