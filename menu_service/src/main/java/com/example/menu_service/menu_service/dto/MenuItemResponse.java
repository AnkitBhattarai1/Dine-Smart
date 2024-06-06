package com.example.menu_service.menu_service.dto;

public record MenuItemResponse(String id, String name, String description, boolean general, String photoLocation) {

    public MenuItemResponse {

    }
}
