package com.example.auth_service.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.auth_service.Models.Authority;

public class SecurityAuthority implements GrantedAuthority {

    private final Authority auth;

    public SecurityAuthority(Authority auth) {
        this.auth = auth;
    }

    @Override
    public String getAuthority() {
        return auth.getName();
    }

}
