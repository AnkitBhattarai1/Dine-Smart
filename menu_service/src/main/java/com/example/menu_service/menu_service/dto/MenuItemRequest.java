package com.example.menu_service.menu_service.dto;

public record MenuItemRequest(
        String name, String description,
        String photoLocation,
        double price,
        boolean isGeneral,
        boolean isVegetarian,
        boolean available,
        String category,
        String ingredients) {

    public MenuItemRequest {
        // Validation is to be done
    }

}
