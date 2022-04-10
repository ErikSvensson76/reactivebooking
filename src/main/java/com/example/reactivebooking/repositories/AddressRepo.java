package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.Address;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressRepo extends ReactiveCrudRepository<Address, String> {
    @Query("SELECT a.* FROM address a WHERE UPPER(a.street) = UPPER(:street) AND UPPER(a.zip_code) = UPPER(:zip) AND UPPER(a.city) = UPPER(:city)")
    Flux<Address> findByStreetZipCodeAndCity(
            @Param("street") String street,
            @Param("zip") String zip,
            @Param("city") String city
    );

    @Query("SELECT COUNT(a.id) FROM address a INNER JOIN premises p ON a.id = p.fk_address_id WHERE p.fk_address_id = :id")
    Mono<Long> countUsageById(@Param("id") String id);

}
