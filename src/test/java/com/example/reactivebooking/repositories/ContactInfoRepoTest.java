package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.ContactInfo;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;
import java.util.UUID;

import static com.example.reactivebooking.TestConstants.SEED_APP_ROLES;
import static com.example.reactivebooking.TestConstants.TEST_DB;
import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@DirtiesContext
class ContactInfoRepoTest {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private ContactInfoRepo underTest;

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
    void createNewContactInfo(){
        ContactInfo contactInfo = new ContactInfo(UUID.randomUUID().toString(), "test@test.com", null);
        StepVerifier.create(underTest.save(contactInfo.setIsNew()))
                .expectNextMatches(result ->  result.getId() != null && result.getId().equals(contactInfo.getId()))
                .verifyComplete();
    }

    @Test
    void findByEmail() {
        String email = "PATIENT1@GMAIL.COM";
        StepVerifier.create(underTest.findByEmail(email))
                .expectNextMatches(contactInfo -> Objects.nonNull(contactInfo) && email.equalsIgnoreCase(contactInfo.getEmail()))
                .verifyComplete();
    }
}