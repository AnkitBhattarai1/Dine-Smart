package com.example.resturant_service.resturant_service.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.resturant_service.resturant_service.Models.Tables;

public interface TableRepo extends JpaRepository<Tables, String> {

    @Query("SELECT MAX(t.tableNo) FROM Tables t")
    Optional<Integer> findMaxTableNo();
}
