package com.example.menu_service.menu_service.Model;

import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @UuidGenerator
    @Column(unique = true, nullable = false, updatable = false)
    private String id;

    @Column
    private String resturantId;
    @Column
    private Set<String> menuItems;

}
