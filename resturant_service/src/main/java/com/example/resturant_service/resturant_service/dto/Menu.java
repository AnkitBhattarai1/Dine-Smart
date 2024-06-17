package com.example.resturant_service.resturant_service.dto;

import java.util.HashSet;
import java.util.Set;

public record Menu(
        String id,
        Set<MenuItem> menuItems) {
}
