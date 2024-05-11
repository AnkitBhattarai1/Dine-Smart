package com.example.user_service.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByPhone(String phone);
}
