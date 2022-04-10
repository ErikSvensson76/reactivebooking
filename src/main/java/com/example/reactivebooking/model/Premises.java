package com.example.reactivebooking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"name", "address", "bookings"}, callSuper = false)
@Table(value = "premises")
public class Premises extends BaseModel<Premises, String> {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "name")
    private String name;
    @Transient
    private Address address;
    @Transient
    private List<Booking> bookings;

    @Override
    @Transient
    public Premises setIsNew() {
        super.isNew = true;
        return this;
    }

    @Override
    @Transient
    public boolean isNew() {
        return super.isNew || id == null;
    }
}
