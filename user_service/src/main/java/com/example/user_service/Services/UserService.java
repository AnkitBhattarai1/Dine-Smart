package com.example.user_service.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;
import com.example.user_service.dto.UpdatingUserRequest;

@Service
public interface UserService {

    public List<ResponseUser> getAllUsers();

    public ResponseUser saveUser(RequestUser userRequest);

    public ResponseUser getUserByEmail(String emaiL);

    public ResponseUser getUserById(String Id);

    public ResponseUser updateUser(UpdatingUserRequest user, String id);

}
