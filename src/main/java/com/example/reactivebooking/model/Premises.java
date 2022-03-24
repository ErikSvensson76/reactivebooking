package com.example.reactivebooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "premises")
public class Premises {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "name")
    private String name;
    @Transient
    private Address address;
    @Transient
    private List<Booking> bookings;
}
