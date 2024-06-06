package com.example.resturant_service.resturant_service.dto;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    private Set<MenuItem> menuItems = new HashSet<>();

    public Menu() {
        // Default constructor
    }

    public Menu(String id, String resturantId, Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

}
