package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.Booking;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface BookingRepo extends ReactiveCrudRepository<Booking, String> {

    String working = "SELECT b.* FROM booking b, premises p, address a " +
            "WHERE b.vacant = :vacant AND b.fk_premises_id = p.id AND p.fk_address_id = a.id AND UPPER(a.city) = UPPER(:city)";

    Flux<Booking> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    Flux<Booking> findByDateTimeBefore(LocalDateTime end);

    Flux<Booking> findByDateTimeAfter(LocalDateTime start);

    Flux<Booking> findByAdministratorId(String administratorId);

    Flux<Booking> findByVaccineType(String vaccineType);

    Flux<Booking> findByVacantIs(boolean vacant);

    @Query("SELECT b.* FROM booking b " +
            "LEFT JOIN premises p ON p.id = b.fk_premises_id " +
            "LEFT JOIN address a ON a.id = p.fk_address_id " +
            "WHERE b.vacant = :vacant AND UPPER(a.city) = UPPER(:city)")
    Flux<Booking> findBookingsByCityAndVacantStatus(@Param("city") String city, @Param("vacant") boolean vacantStatus);

    @Query("SELECT b.* FROM booking b " +
            "INNER JOIN premises p ON b.fk_premises_id = p.id " +
            "WHERE p.id = :premisesId AND b.vacant = :vacant")
    Flux<Booking> findBookingsByPremisesIdAndVacantStatus(@Param("premisesId") String premisesId, @Param("vacant") boolean vacantStatus);

    @Query("SELECT b.* FROM booking b " +
            "INNER JOIN premises p ON b.fk_premises_id = p.id " +
            "WHERE p.id = :premisesId")
    Flux<Booking> findAllByPremisesId(@Param("premisesId") String premisesId);

    @Query("SELECT b.* FROM booking b " +
            "INNER JOIN patient p on b.fk_patient_id = p.id " +
            "WHERE p.id = :patientId")
    Flux<Booking> findAllByPatientId(@Param("patientId") String patientId);
}
