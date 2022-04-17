package com.example.reactivebooking.repositories;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static com.example.reactivebooking.TestConstants.SEED_APP_ROLES;
import static com.example.reactivebooking.TestConstants.TEST_DB;
import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
class BookingRepoTest {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private BookingRepo underTest;

    private void executeSqlScriptBlocking() {
        Mono.from(connectionFactory.create())
                .flatMap(connection -> ScriptUtils.executeSqlScript(connection, TEST_DB)
                        .then(Mono.from(connection.close())))
                .block();
    }

    private void runSqlSeedScriptBlocking(){
        Mono.from(connectionFactory.create())
                .flatMap(connection -> ScriptUtils.executeSqlScript(connection, SEED_APP_ROLES)
                        .then(Mono.from(connection.close())))
                .block();
    }

    @BeforeEach
    void setUp() {
        executeSqlScriptBlocking();
        runSqlSeedScriptBlocking();
    }

    @Test
    void findByDateTimeBetween() {
        LocalDateTime start = LocalDateTime.parse("2022-01-01T15:10");
        LocalDateTime end = LocalDateTime.parse("2022-01-01T15:15");
        long expectedCount = 2;
        StepVerifier.create(underTest.findByDateTimeBetween(start, end))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }

    @Test
    void findByDateTimeBefore() {
        LocalDateTime dateTime = LocalDateTime.parse("2022-01-02T00:00");
        long expectedCount = 3;
        StepVerifier.create(underTest.findByDateTimeBefore(dateTime))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }

    @Test
    void findByDateTimeAfter() {
        LocalDateTime dateTime = LocalDateTime.parse("2022-01-02T07:01");
        long expectedCount = 1;
        StepVerifier.create(underTest.findByDateTimeAfter(dateTime))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }

    @Test
    void findByAdministratorId() {
        String administratorId = "test";
        long expectedCount = 1;
        StepVerifier.create(underTest.findByAdministratorId(administratorId))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }

    @Test
    void findByVaccineType() {
        String vaccineType = "Säsongsinfluensa";
        long expectedCount = 1;
        StepVerifier.create(underTest.findByVaccineType(vaccineType))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findByVacantIsTrue() {
        long expectedCount = 3;
        StepVerifier.create(underTest.findByVacantIs(true))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }

    @Test
    void findByVacantIsFalse(){
        long expectedCount = 2;
        StepVerifier.create(underTest.findByVacantIs(false))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }

    @Test
    void findBookingsByCityAndVacantStatus_true() {
        String city = "Växjö";
        long count = 2;
        StepVerifier.create(underTest.findBookingsByCityAndVacantStatus(city, true))
                .expectNextCount(count)
                .verifyComplete();
    }

    @Test
    void findBookingsByCityAndVacantStatus_false() {
        String city = "Växjö";
        long count = 1;
        StepVerifier.create(underTest.findBookingsByCityAndVacantStatus(city, false))
                .expectNextCount(count)
                .verifyComplete();

    }

    @Test
    void findBookingsByPremisesIdAndVacantStatus() {
        String premisesId = "premises2";
        long count = 1;
        StepVerifier.create(underTest.findBookingsByPremisesIdAndVacantStatus(premisesId, false))
                .expectNextCount(count)
                .verifyComplete();
    }

    @Test
    void findAllByPremisesId() {
        String premisesId = "premises1";
        long count = 3;
        StepVerifier.create(underTest.findAllByPremisesId(premisesId))
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findAllByPatientId() {
        String patientId = "patient2";
        long expectedCount = 1;
        StepVerifier.create(underTest.findAllByPatientId(patientId))
                .expectNextCount(expectedCount)
                .verifyComplete();
    }
}