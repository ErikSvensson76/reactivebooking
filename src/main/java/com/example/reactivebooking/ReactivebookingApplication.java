package com.example.reactivebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class ReactivebookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactivebookingApplication.class, args);
    }

}
