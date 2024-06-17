package com.example.menu_service.menu_service.dto;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.example.menu_service.menu_service.Model.Menu;

public record Resturant(String id,
        String name,
        Address address,
        Location location,
        String email,
        String menuId,
        Menu menu) {

    public Resturant {

    }

}
