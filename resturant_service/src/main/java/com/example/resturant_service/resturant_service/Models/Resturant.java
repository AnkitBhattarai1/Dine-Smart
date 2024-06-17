package com.example.resturant_service.resturant_service.Models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "resturants")
public class Resturant {

    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Embedded
    private Address address;

    @Column
    private String phone;

    @Embedded
    private Location location;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    @JsonProperty("createdAt")
    private LocalDateTime cratedDateTime;

    @Column(name = "menu_Id")
    @JsonProperty("menuId")
    String menuId;

    @OneToMany(mappedBy = "resturant")
    @JsonProperty("tables")
    @JsonManagedReference
    private Set<Tables> tables = new HashSet<>();

    public Resturant(String name, Address address, String phone, Location location, String email,
            LocalDateTime cratedDateTime, String menuId, Set<Tables> tables) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.location = location;
        this.email = email;
        this.cratedDateTime = cratedDateTime;
        this.menuId = menuId;
        this.tables = tables;
    }

    public Resturant() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCratedDateTime() {
        return cratedDateTime;
    }

    public void setCratedDateTime(LocalDateTime cratedDateTime) {
        this.cratedDateTime = cratedDateTime;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Set<Tables> getTables() {
        return tables;
    }

    public void setTables(Set<Tables> tables) {
        this.tables = tables;
    }

}
