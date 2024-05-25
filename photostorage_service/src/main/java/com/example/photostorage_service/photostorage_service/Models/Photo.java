package com.example.photostorage_service.photostorage_service.Models;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Photo {
    @Id
    @UuidGenerator
    @Column(unique = true, updatable = false)
    private String id;

    @Column
    private String location;

    @Column
    private String photoType;

    public Photo() {

    }

    public String getId() {
        return this.id;
    }

    public String getlocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    @Override
    public String toString() {
        return "Photo [id=" + id + ", location=" + location + ", photoType=" + photoType + "]";
    }

}
