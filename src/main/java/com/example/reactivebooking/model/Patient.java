package com.example.reactivebooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "patient")
public class Patient {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "pnr")
    private String pnr;
    @Column(value = "first_name")
    private String firstName;
    @Column(value = "last_name")
    private String lastName;
    @Column(value = "birth_date")
    private LocalDate birthDate;
    @Transient
    private ContactInfo contactInfo;
    @Transient
    private AppUser userCredentials;
    @Transient
    private List<Booking> vaccineBookings;
}
