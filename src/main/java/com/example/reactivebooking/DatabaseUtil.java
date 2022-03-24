package com.example.reactivebooking;

import com.example.reactivebooking.model.AppRole;
import com.example.reactivebooking.model.UserRole;
import com.example.reactivebooking.repositories.AppRoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@RequiredArgsConstructor

@Slf4j
public class DatabaseUtil {
    private final AppRoleRepo appRoleRepo;

    @PostConstruct
    public void initialize(){

        var amount = appRoleRepo.count().block();
        if(amount != null && amount == 0){
            for(UserRole userRole : UserRole.values()){
                AppRole appRole = new AppRole(UUID.randomUUID().toString(), userRole.name(),  null);
                appRoleRepo.save(appRole.setIsNew()).block();
            }
        }


    }
}
