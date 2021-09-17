package com.lb.messageservice.app.service;

import mocks.ReportMocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ChannelMapperTest {
    ChannelMapper channelMapper;

    @BeforeEach
    public void setUp() {
        ChannelFactory channelFactory = new ChannelFactory();
        this.channelMapper = new ChannelMapper(channelFactory);
    }

    @Test
    @DisplayName("given a ReportDTO, it should return a Report")
    public void toReportSuccess() {
        // given
        var dto = ReportMocked.reportDtoScheduled;
        // exec
        var result = channelMapper.toReport(dto);
        // should
        assertThat(result.message()).isEqualTo(dto.message());
        assertThat(result.sendDate()).isEqualTo(dto.sendDate());
        assertThat(result.recipient()).isEqualTo(dto.recipient());
        assertThat(result.channel().name()).isEqualTo(dto.channel().toString());
        assertThat(result.status()).isEqualTo(dto.status());
    }

    @Test
    @DisplayName("given a Report, it should return a ReportDto")
    public void fromReport2DtoSuccess() {
        // given
        var report = ReportMocked.reportScheduled;
        // exec
        var result = channelMapper.fromReport2Dto(report);
        // should
        assertThat(result.message()).isEqualTo(report.message());
        assertThat(result.sendDate()).isEqualTo(report.sendDate());
        assertThat(result.recipient()).isEqualTo(report.recipient());
        assertThat(result.channel().toString()).isEqualTo(report.channel().name());
        assertThat(result.status()).isEqualTo(report.status());
    }

    @Test
    @DisplayName("given a ReportDAO, it should return a Report")
    public void toReportFromReportDaoSuccess() {
        // given
        var dao = ReportMocked.reportDAOScheduled;
        // exec
        var result = channelMapper.toReport(dao);
        // should
        assertThat(result.message()).isEqualTo(dao.getMessage());
        assertThat(result.sendDate()).isEqualTo(dao.getSendDate());
        assertThat(result.recipient()).isEqualTo(dao.getRecipient());
        assertThat(result.channel().name()).isEqualTo(dao.getChannel().toString());
        assertThat(result.status()).isEqualTo(dao.getStatus());
    }

    @Test
    @DisplayName("given a Report, it should return a ReportDao")
    public void fromReport2DaoSuccess() {
        // given
        var report = ReportMocked.reportScheduled;
        // exec
        var result = channelMapper.fromReport2Dao(report);
        // should
        assertThat(result.getMessage()).isEqualTo(report.message());
        assertThat(result.getSendDate()).isEqualTo(report.sendDate());
        assertThat(result.getRecipient()).isEqualTo(report.recipient());
        assertThat(result.getChannel().toString()).isEqualTo(report.channel().name());
        assertThat(result.getStatus()).isEqualTo(report.status());
    }
}