package com.example.reactivebooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "contact_info")
public class ContactInfo {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "email")
    private String email;
    @Column(value = "phone")
    private String phone;
}
