package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.AppRole;
import com.example.reactivebooking.model.UserRole;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.example.reactivebooking.TestConstants.TEST_DB;

@DataR2dbcTest
class AppRoleRepoTest {

    @Autowired
    ConnectionFactory connectionFactory;
    @Autowired
    AppRoleRepo underTest;

    private void executeSqlScriptBlocking() {
        Mono.from(connectionFactory.create())
                .flatMap(connection -> ScriptUtils.executeSqlScript(connection, TEST_DB)
                        .then(Mono.from(connection.close())))
                .block();
    }

    @BeforeEach
    void setUp() {
        executeSqlScriptBlocking();
    }

    @Test
    void findByUserRole() {
        underTest.save(new AppRole("testId", UserRole.ROLE_PATIENT_USER.name(), null).setIsNew()).log().block();

        StepVerifier.create(underTest.findByUserRole(UserRole.ROLE_PATIENT_USER.name()))
                .expectNextMatches(appRole -> appRole.getUserRole().equals("ROLE_PATIENT_USER"))
                .verifyComplete();
    }


}