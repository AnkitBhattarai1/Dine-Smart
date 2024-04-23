package com.example.user_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
