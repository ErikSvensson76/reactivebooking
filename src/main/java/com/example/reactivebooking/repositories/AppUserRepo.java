package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.AppUser;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AppUserRepo extends ReactiveCrudRepository<AppUser, String> {

    @Query("SELECT * from app_user a " +
            "WHERE UPPER(a.username) = UPPER(:username)")
    Mono<AppUser> findByUsername(@Param("username") String username);

    @Query("SELECT a.* from app_user a  " +
            "JOIN patient p ON a.id = p.fk_app_user_id " +
            "WHERE p.id = :patientId")
    Mono<AppUser> findByPatientId(@Param("patientId") String patientId);

    @Query("SELECT a.* FROM app_user a " +
            "JOIN role_app_user rau on a.id = rau.fk_app_user_id " +
            "JOIN app_role ar on ar.id = rau.fk_app_role_id " +
            "WHERE UPPER(ar.user_role) = :userRole")
    Flux<AppUser> findByUserRole(@Param("userRole") String userRole);

    @Modifying
    @Query("INSERT INTO role_app_user (fk_app_role_id, fk_app_user_id) VALUES(:appRoleId, :userId)")
    Mono<Void> addAppRoleToAppUser(@Param("userId") String userId,@Param("appRoleId") String appRoleId);
}
