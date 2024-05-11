package com.example.rating_review_service.rating_review_service.Models;

import jakarta.persistence.Entity;

@Entity
public class Review {
    private String id;
    private String review;
    private String userId;
    private String hotelId;

}
