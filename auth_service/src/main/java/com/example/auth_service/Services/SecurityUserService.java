package com.example.auth_service.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.DTO.Auth;
import com.example.auth_service.Models.User;
import com.example.auth_service.Repo.UserRepo;
import com.example.auth_service.Security.SecurityUser;
import com.example.auth_service.Utils.JwtUtils;

@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils j;

    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    // loads the user for authentication...
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var optionalUser = userRepo.findByEmail(email);
        return optionalUser.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));
    }

    // save the user in the database...
    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User Added to the System";

    }

    // Generate the token if the user is authenticated and deliver it ...
    public String generateToken(Auth auth) {
        Authentication a = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(auth.email(),
                        auth.password()));

        if (a.isAuthenticated()) {
            return j.generateToken(auth.email());
        } else {
            throw new RuntimeException("Invalid Credentials");
        }
    }

    public void validateToken(String token) {
        j.validateToken(token);
    }

}
