package com.example.reactivebooking.repositories;

import com.example.reactivebooking.model.Premises;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PremisesRepo extends ReactiveCrudRepository<Premises, String> {
    
}
