package com.example.menu_service.menu_service.Model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @UuidGenerator
    @Column(unique = true, updatable = false, nullable = false)
    String id;

    @Column()
    String name;

    @Column()
    String description;

    @Column()
    String photoLocation;

    @Column()
    boolean general;

    public MenuItem() {
    }

    public MenuItem(String name, String description, String photoLocation) {
        this.name = name;
        this.description = description;
        this.photoLocation = photoLocation;
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

    @Override
    public String toString() {
        return "MenuItem [id=" + id + ", name=" + name + ", description=" + description + ", photoLocation="
                + photoLocation + ", general=" + general + "]";
    }

}
