package com.example.resturant_service.resturant_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resturant_service.resturant_service.Models.Resturant;

public interface ResturantRepo extends JpaRepository<Resturant, String> {

}
