package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.Address;
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
import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
class AddressRepoTest {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private AddressRepo underTest;

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
    void findByStreetZipCodeAndCity() {
        Address expected = new Address("address1", "Storgatan 1", "35233", "Växjö");
        StepVerifier.create(underTest.findByStreetZipCodeAndCity("storgatan 1", "35233", "växjö"))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void countUsageById() {
        long expected = 1L;
        StepVerifier.create(underTest.countUsageById("address1"))
                .expectNextCount(expected)
                .verifyComplete();
    }
}