package com.example.menu_service.menu_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.menu_service.menu_service.Model.MenuItem;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, String> {

}
