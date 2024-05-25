package com.example.menu_service.menu_service.dto;

import java.util.Set;

/**
 * MenuResponse
 */
public record MenuResponse(String id, Set<MenuItemResponse> menuItems) {
    public MenuResponse {
    }

}