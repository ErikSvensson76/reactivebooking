package com.example.reactivebooking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"dateTime", "price", "administratorId", "vaccineType", "vacant", "patient", "premises"})
@Table(value = "booking")
public class Booking {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "date_time")
    private LocalDateTime dateTime;
    @Column(value = "price")
    private BigDecimal price;
    @Column(value = "administrator_id")
    private String administratorId;
    @Column(value = "vaccine_type")
    private String vaccineType;
    @Column(value = "vacant")
    private boolean vacant;
    @Transient
    private Patient patient;
    @Transient
    private Premises premises;
}
