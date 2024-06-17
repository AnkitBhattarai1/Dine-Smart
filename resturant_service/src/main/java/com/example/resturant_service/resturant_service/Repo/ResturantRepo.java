package com.example.resturant_service.resturant_service.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resturant_service.resturant_service.Models.Resturant;

@Repository
public interface ResturantRepo extends JpaRepository<Resturant, String> {

    // Optional<Resturant> findById(String resturantId);

    Optional<Resturant> findByEmail(String email);
}
