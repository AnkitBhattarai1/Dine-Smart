package com.example.user_service.Models;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * User
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false)
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String status;
    private String photoUrl;

}