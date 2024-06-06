package com.example.menu_service.menu_service.Model;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_items")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {

    @Id
    @UuidGenerator
    @Column(unique = true, updatable = false, nullable = false)
    String id;

    @Column()
    String name;

    @Column(length = 500)
    String description;

    @Column()
    String photoLocation;

    @Column()
    boolean general;

    @Column
    boolean vegetarian;

    @Column
    double price;

    @Column()
    String available;

    @Column()
    String category;

    @Column
    String ingredients;

}
