package com.example.resturant_service.resturant_service.dto;

import com.example.resturant_service.resturant_service.Models.Address;
import com.example.resturant_service.resturant_service.Models.Location;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResturantResponse(
        String id,
        String name,
        Address address,
        Location location,
        String email,
        String menuId,
        Menu menu) {

    public ResturantResponse {

    }
}
