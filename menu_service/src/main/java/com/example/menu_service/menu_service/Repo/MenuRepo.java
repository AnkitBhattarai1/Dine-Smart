package com.example.menu_service.menu_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.menu_service.menu_service.Model.Menu;
import java.util.Optional;

@Repository
public interface MenuRepo extends JpaRepository<Menu, String> {

    public Optional<Menu> findByResturantId(String resturantId);
}
