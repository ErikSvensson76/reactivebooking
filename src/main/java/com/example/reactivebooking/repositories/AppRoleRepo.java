package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.AppRole;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AppRoleRepo extends ReactiveCrudRepository<AppRole, String> {

    Mono<AppRole> findByUserRole(String userRole);
    @Query("SELECT r.* from app_role r " +
            "JOIN role_app_user rau on r.id = rau.fk_app_role_id JOIN app_user a on a.id = rau.fk_app_user_id " +
            "WHERE a.id = :appUserId")
    Flux<AppRole> findByAppUserId(@Param("appUserId") String appUserId);

}
