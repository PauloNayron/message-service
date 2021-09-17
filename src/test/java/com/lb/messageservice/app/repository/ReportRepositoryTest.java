package com.lb.messageservice.app.repository;

import com.lb.messageservice.app.repository.dao.ReportDAO;
import mocks.ReportMocked;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("Test")
@DataJpaTest
class ReportRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ReportRepository repository;

    @Test
    @DisplayName("given the id of an existing reportDAO, you should refer to it")
    public void reportFindByIdSuccess() {
        // given
        ReportDAO reportDAO = ReportMocked.reportDAOScheduled;
        entityManager.persist(reportDAO);
        //exec
        Optional<ReportDAO> resultFound = repository.findById(reportDAO.getId());
        // should
        assertThat(resultFound.isPresent()).isTrue();
    }

    @Test
    @DisplayName("given an existing reportDAO, it should return")
    public void reportFindSuccess() {
        // given
        ReportDAO dao = ReportMocked.reportDAOPushed;
        entityManager.persist(dao);
        // exec
        Optional<ReportDAO> resultFound =
                repository.findReport(
                        dao.getChannel().ordinal(),
                        dao.getMessage(),
                        dao.getRecipient(),
                        dao.getSendDate()
                );
        // should
        assertThat(resultFound.isPresent()).isTrue();
    }
}