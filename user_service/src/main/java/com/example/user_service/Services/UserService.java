package com.example.user_service.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user_service.Models.User;
import com.example.user_service.Repo.UserRepo;
import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;

@Service
public interface UserService {

    public List<User> getAllUsers();

    public ResponseUser saveUser(RequestUser userRequest);

}
