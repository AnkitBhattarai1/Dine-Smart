package com.example.resturant_service.resturant_service.dto;

import java.time.LocalDateTime;

import com.example.resturant_service.resturant_service.Models.Address;
import com.example.resturant_service.resturant_service.Models.Location;

public record ResturantRequest(
        String name,
        Address address,
        Location location,
        String email,
        String phone,
        LocalDateTime createdAt) {

    public ResturantRequest {

    }
}
