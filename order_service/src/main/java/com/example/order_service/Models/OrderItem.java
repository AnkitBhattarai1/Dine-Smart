package com.example.order_service.Models;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderItem")
public class OrderItem {

    @Id
    @UuidGenerator
    @Column(unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    String menuitemId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    public OrderItem(String name, String menuitemId, int quantity, double price, Order order) {
        this.name = name;
        this.menuitemId = menuitemId;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public OrderItem() {
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

    public String getMenuitemId() {
        return menuitemId;
    }

    public void setMenuitemId(String menuitemId) {
        this.menuitemId = menuitemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
