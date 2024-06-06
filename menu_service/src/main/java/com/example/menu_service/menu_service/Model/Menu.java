package com.example.menu_service.menu_service.Model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @UuidGenerator
    @Column(unique = true, nullable = false, updatable = false)
    private String id;

    @Column()
    private String resturantId;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_menuitems", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "menuItem_Id"))
    private Set<MenuItem> menuItems = new HashSet<>();

    public Menu() {
    }

    public Menu(String resturantId, Set<MenuItem> menuItems) {
        this.resturantId = resturantId;
        this.menuItems = menuItems;
    }

    public String getId() {
        return id;
    }

    public String getResturantId() {
        return resturantId;
    }

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", resturantId=" + resturantId + ", menuItems=" + menuItems + "]";
    }

}
