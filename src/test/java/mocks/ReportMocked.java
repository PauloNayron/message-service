package mocks;

import com.lb.messageservice.app.commons.ChannelType;
import com.lb.messageservice.app.controller.dto.ReportDTO;
import com.lb.messageservice.app.repository.dao.ReportDAO;
import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.entity.channels.WhatsappChannel;
import com.lb.messageservice.domain.entity.enums.Status;

import java.time.LocalDateTime;

public class ReportMocked {
    public static LocalDateTime localDateTime =
            LocalDateTime.of(
                    2022,
                    2,
                    1,
                    0,
                    0,
                    0,
                    0
            );

    public static Report reportScheduled =
            new Report(
                    1L,
                    "Feliz Ano Novo!",
                    localDateTime,
                    1l,
                    new WhatsappChannel(),
                    Status.SCHEDULED
            );

    public static ReportDTO reportDtoScheduled =
            new ReportDTO(
                    1l,
                    localDateTime,
                    "Feliz Ano Novo!",
                    ChannelType.SMS,
                    Status.SCHEDULED
            );

    public static ReportDAO reportDAOScheduled =
            new ReportDAO(
                    null,
                    "Feliz Ano Novo!",
                    localDateTime,
                    1l,
                    ChannelType.SMS,
                    Status.SCHEDULED,
                    localDateTime,
                    localDateTime
            );

    public static ReportDAO reportDAOPushed =
            new ReportDAO(
                    null,
                    "Feliz Ano Novo!",
                    localDateTime,
                    2l,
                    ChannelType.SMS,
                    Status.PUSHED,
                    localDateTime,
                    localDateTime
            );
}
