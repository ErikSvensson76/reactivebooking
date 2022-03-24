package com.example.reactivebooking;

import com.example.reactivebooking.model.AppRole;
import com.example.reactivebooking.model.UserRole;
import com.example.reactivebooking.repositories.AppRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class DatabaseUtil {
    private final AppRoleRepo appRoleRepo;

    @PostConstruct
    public void initialize(){
        var amount = appRoleRepo.count();
        amount.subscribe(result -> {
            if(result == 0){
                appRoleRepo.saveAll(
                        Arrays.stream(UserRole.values())
                                .map(userRole -> new AppRole(UUID.randomUUID().toString(), userRole, null))
                                .collect(Collectors.toList())
                );
            }
        });
    }
}
