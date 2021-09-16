package mocks;

import com.lb.messageservice.domain.entity.Report;
import com.lb.messageservice.domain.entity.channels.WhatsappChannel;
import com.lb.messageservice.domain.entity.enums.Status;

import java.time.LocalDateTime;

public class ReportMocked {
    static public Report reportScheduled = new Report(1L, "Feliz Ano Novo!",LocalDateTime.now(), 1l, new WhatsappChannel(), Status.SCHEDULED);
}
