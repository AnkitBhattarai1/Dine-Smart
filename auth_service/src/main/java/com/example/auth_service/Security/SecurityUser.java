package com.example.auth_service.Security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth_service.Models.User;

public class SecurityUser implements UserDetails {

    private final User u;

    public SecurityUser(User u) {
        this.u = u;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return u.getAuthorities().stream()
                .map(SecurityAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return u.getPassword();
    }

    @Override
    public String getUsername() {
        return u.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
