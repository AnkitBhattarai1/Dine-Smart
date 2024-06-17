package com.example.resturant_service.resturant_service.Models;

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
@Table(name = "resturant_table")
public class Tables {

    @Id
    @UuidGenerator
    @Column(unique = true, nullable = false, updatable = false)
    String id;

    @Column(name = "table_no", unique = true)
    int tableNo;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resturant_id", referencedColumnName = "id")
    @JsonBackReference
    private Resturant resturant;

    @Column(name = "qr")
    String qr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
