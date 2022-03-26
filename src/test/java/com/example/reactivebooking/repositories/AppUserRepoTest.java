package com.example.reactivebooking.repositories;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import reactor.core.publisher.Mono;

import static com.example.reactivebooking.TestConstants.SEED_APP_ROLES;
import static com.example.reactivebooking.TestConstants.TEST_DB;

@DataR2dbcTest
class AppUserRepoTest {

    @Autowired
    private ConnectionFactory connectionFactory;

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
    }

    @Test
    void findByPatientId() {
    }

    @Test
    void findByUserRole() {
    }


}