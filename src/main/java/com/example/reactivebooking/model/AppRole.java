package com.example.reactivebooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "app_role")
public class AppRole extends BaseModel<AppRole, String> {

    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "user_role")
    private String userRole;
    @Transient
    private Set<AppUser> appUsers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return userRole.equals(appRole.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRole);
    }

    @Transient
    @Override
    public AppRole setIsNew() {
        super.isNew = true;
        return this;
    }

    @Transient
    @Override
    public boolean isNew() {
        return super.isNew || id == null;
    }

}
