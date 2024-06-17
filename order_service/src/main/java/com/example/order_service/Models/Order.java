package com.example.order_service.Models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @UuidGenerator
    @Column(unique = true, nullable = false, updatable = false)
    String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "resturant_id")
    private String resturantId;

    @Column(name = "order_date", updatable = false)
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderItem> items = new HashSet<>();

    @Column(name = "total_amount")
    private double totalAmount;

    public Order(String customerId, LocalDateTime orderDate, Set<OrderItem> items, double totalAmount) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getResturantId() {
        return resturantId;
    }

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

}
