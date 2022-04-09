package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.Patient;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientRepo extends ReactiveCrudRepository<Patient, String> {
    @Query("SELECT p.* FROM patient p " +
            "INNER JOIN app_user au ON p.fk_app_user_id = au.id " +
            "WHERE UPPER(au.username) = UPPER(:username)")
    Mono<Patient> findByUsername(@Param("username") String username);

    Mono<Patient> findByPnr(String pnr);

    @Query("SELECT p.* FROM patient p " +
            "WHERE UPPER(CONCAT(p.first_name, ' ',p.last_name)) LIKE UPPER(CONCAT('%',:name,'%'))")
    Flux<Patient> searchByName(@Param("name") String name);

    @Query("SELECT p.* FROM patient p " +
            "INNER JOIN booking b ON p.id = b.fk_patient_id " +
            "WHERE b.id = :bookingId")
    Mono<Patient> findPatientByBookingId(@Param("bookingId") String bookingId);
}

