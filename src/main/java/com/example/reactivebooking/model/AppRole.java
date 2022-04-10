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
@EqualsAndHashCode(exclude = {"appUsers"}, callSuper = false)
@Table(value = "app_role")
public class AppRole extends BaseModel<AppRole, String> {

    @Id
    @Column(value = "id")
    private String id;
    @Column(value = "user_role")
    private String userRole;
    @Transient
    private Set<AppUser> appUsers;



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
