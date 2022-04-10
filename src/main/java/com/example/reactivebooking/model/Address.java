package com.example.reactivebooking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"streetAddress", "zipCode", "city"})
@Table(value = "address")
public class Address {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "street")
    private String streetAddress;
    @Column(value = "zip_code")
    private String zipCode;
    @Column(value = "city")
    private String city;
}
