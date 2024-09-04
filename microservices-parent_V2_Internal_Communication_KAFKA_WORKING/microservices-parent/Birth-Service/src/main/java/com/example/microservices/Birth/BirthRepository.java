package com.example.microservices.Birth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BirthRepository extends JpaRepository<Birth,Long>{
    
}
