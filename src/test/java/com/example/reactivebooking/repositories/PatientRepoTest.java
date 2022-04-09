package com.example.reactivebooking.repositories;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.example.reactivebooking.TestConstants.SEED_APP_ROLES;
import static com.example.reactivebooking.TestConstants.TEST_DB;

@DataR2dbcTest
class PatientRepoTest {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private PatientRepo underTest;

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
    void findByUsername() {
        String username = "patient1";
        StepVerifier.create(underTest.findByUsername(username))
                .expectNextMatches(patient -> {
                    assert patient.getId() != null;
                    return patient.getId().equals("patient1");
                })
                .verifyComplete();
    }

    @Test
    void findByPnr() {
        String pnr = "199001012424";
        StepVerifier.create(underTest.findByPnr(pnr))
                .expectNextMatches(patient -> patient.getPnr().equals(pnr))
                .verifyComplete();
    }

    @Test
    void searchByName() {
        String name = "SON";
        StepVerifier.create(underTest.searchByName(name))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findPatientByBookingId() {
        String bookingId = "booking1";
        String expectedPatientId = "patient1";
        StepVerifier.create(underTest.findPatientByBookingId(bookingId).log())
                .expectNextMatches(patient -> patient != null && expectedPatientId.equals(patient.getId()))
                .verifyComplete();
    }
}