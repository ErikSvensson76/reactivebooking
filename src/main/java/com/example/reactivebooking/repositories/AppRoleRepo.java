package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.AppRole;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AppRoleRepo extends ReactiveCrudRepository<AppRole, String> {

    Mono<AppRole> findByUserRole(String userRole);

}
