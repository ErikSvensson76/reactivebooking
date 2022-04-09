package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.ContactInfo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ContactInfoRepo extends ReactiveCrudRepository<ContactInfo, String> {

    @Query("SELECT ci.* FROM contact_info ci WHERE UPPER(email) = UPPER(:email)")
    Mono<ContactInfo> findByEmail(@Param("email") String email);

}
