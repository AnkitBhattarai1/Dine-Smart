package com.example.menu_service.menu_service.Model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class MenuItems {

    @Id
    @UuidGenerator
    @Column(unique = true, updatable = false, nullable = false)
    String id;

    boolean general;
    String name;
    String description;
}
