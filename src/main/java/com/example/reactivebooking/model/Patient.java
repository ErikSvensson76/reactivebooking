package com.example.reactivebooking.model;

import lombok.*;
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
@EqualsAndHashCode(exclude = {"pnr", "firstName", "lastName", "birthDate", "contactInfo", "userCredentials", "vaccineBookings"}, callSuper = false)
@Table(value = "patient")
public class Patient extends BaseModel<Patient, String>{
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


    @Transient
    @Override
    public Patient setIsNew() {
        super.isNew = true;
        return this;
    }

    @Transient
    @Override
    public boolean isNew() {
        return super.isNew || id == null;
    }
}
