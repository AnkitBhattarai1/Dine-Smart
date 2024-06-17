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
    private String firstName;
    private String lastName;
    private String middleName;
    // private String password;
    private String email;
    private String phone;

    private String address;
    private boolean isRegistered;

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    // public String getPassword() {
    // return password;
    // }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

}