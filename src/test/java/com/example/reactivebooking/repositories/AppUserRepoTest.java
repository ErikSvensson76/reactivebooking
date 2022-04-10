package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.AppUser;
import com.example.reactivebooking.model.UserRole;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static com.example.reactivebooking.TestConstants.SEED_APP_ROLES;
import static com.example.reactivebooking.TestConstants.TEST_DB;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
@DirtiesContext
class AppUserRepoTest {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private AppUserRepo underTest;

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
                .expectNextMatches(appUser -> appUser.getUsername().equals(username))
                .verifyComplete();

    }

    @Test
    void findByPatientId() {
        String patientId = "patient1";
        StepVerifier.create(underTest.findByPatientId(patientId))
                .expectNextMatches(appUser -> appUser != null && appUser.getId() != null &&  appUser.getId().equals("user1"))
                .verifyComplete();
    }

    @Test
    void findByUserRole() {
        String userRole = UserRole.ROLE_PATIENT_USER.name();
        StepVerifier.create(underTest.findByUserRole(userRole))
                .expectNextMatches(appUser -> appUser.getUsername().equals("patient1"))
                .verifyComplete();
    }

    @Test
    void addAppRoleToAppUser() {
        AppUser appUser = new AppUser(UUID.randomUUID().toString(), "password", "olle.svensson", null, null);
        appUser = underTest.save(appUser.setIsNew()).block();
        assertNotNull(appUser);

        StepVerifier.create(underTest.addAppRoleToAppUser(appUser.getId(), "role1"))
                .verifyComplete();

        StepVerifier.create(underTest.findByUserRole(UserRole.ROLE_PATIENT_USER.name()))
                .expectNextCount(2)
                .verifyComplete();
    }
}