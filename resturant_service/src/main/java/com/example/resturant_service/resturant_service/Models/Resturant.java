package com.example.resturant_service.resturant_service.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resturants")
public class Resturant {

    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Column
    private String phone;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    private LocalDateTime cratedDateTime;

}
