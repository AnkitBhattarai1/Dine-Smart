package com.example.auth_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.DTO.Auth;
import com.example.auth_service.Models.User;
import com.example.auth_service.Services.SecurityUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    SecurityUserService securityUserService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return securityUserService.saveUser(user);
    }

    @PostMapping("/getToken")
    public String getToken(@RequestBody Auth user) {
        return securityUserService.generateToken(user);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        securityUserService.validateToken(token);
        return "Validated";
    }

}
