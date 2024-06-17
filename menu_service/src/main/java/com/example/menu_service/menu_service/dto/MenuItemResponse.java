package com.example.menu_service.menu_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MenuItemResponse(String id,
        String name,
        String description,
        String photoLocation,
        boolean vegetarian,
        double price,
        boolean available,
        String category,
        String ingredients) {

    public MenuItemResponse {

    }
}
