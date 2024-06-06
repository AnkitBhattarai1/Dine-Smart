package com.example.menu_service.menu_service.dto;

import java.util.Set;

public record MenuRequest(String resturantId, Set<MenuItemRequest> menuItems) {

    public MenuRequest {

        // TODO Validation is to be done

    }

}
