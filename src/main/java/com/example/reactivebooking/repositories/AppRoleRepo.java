package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.AppRole;
import com.example.reactivebooking.model.UserRole;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AppRoleRepo extends ReactiveCrudRepository<AppRole, String> {

    Mono<AppRole> findByUserRole(UserRole userRole);

}
