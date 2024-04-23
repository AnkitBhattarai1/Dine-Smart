package com.example.user_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.Services.UserService;
import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseUser saveUser(@RequestBody RequestUser user) {
        return userService.saveUser(user);
    }
}
