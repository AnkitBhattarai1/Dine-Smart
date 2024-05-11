package com.example.rating_review_service.rating_review_service.Models;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Id;

public class Rating {
    @Id
    @UuidGenerator
    private String Id;
    private String userId;
    private String resturantId;
}
