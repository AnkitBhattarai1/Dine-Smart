package com.example.resturant_service.resturant_service.Models;

import jakarta.persistence.Embeddable;

@Embeddable
/**
 * Address
 */
public record Address(
        String province,
        String district,
        String city,
        String detailedPlace) {
}
