package com.example.reactivebooking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"password", "roles", "patient"}, callSuper = false)
@Table(value = "app_user")
public class AppUser extends BaseModel<AppUser, String> {
    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "password")
    private String password;
    @Column(value = "username")
    private String username;
    @Transient
    private Set<AppRole> roles;
    @Transient
    private Patient patient;

    @Transient
    @Override
    public AppUser setIsNew() {
        super.isNew = true;
        return this;
    }

    @Transient
    @Override
    public boolean isNew() {
        return super.isNew || id == null;
    }
}
