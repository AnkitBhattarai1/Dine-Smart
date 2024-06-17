package com.example.menu_service.menu_service.Model;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.micrometer.common.lang.Nullable;
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

    @Column(nullable = false)
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
    boolean available;

    @Column()
    String category;

    @Column
    String ingredients;

    public MenuItem() {
    }

    public MenuItem(String name, String description, String photoLocation, boolean general, boolean vegetarian,
            double price, boolean available, String category, String ingredients) {
        this.name = name;
        this.description = description;
        this.photoLocation = photoLocation;
        this.general = general;
        this.vegetarian = vegetarian;
        this.price = price;
        this.available = available;
        this.category = category;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public boolean isGeneral() {
        return general;
    }

    public void setGeneral(boolean general) {
        this.general = general;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
