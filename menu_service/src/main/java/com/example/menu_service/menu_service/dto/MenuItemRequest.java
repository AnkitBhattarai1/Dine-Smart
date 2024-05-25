package com.example.menu_service.menu_service.dto;

import org.springframework.web.multipart.MultipartFile;

public record MenuItemRequest(
        String name, String description, MultipartFile photo) {

    public MenuItemRequest {
        // Validation is to be done
    }

}
