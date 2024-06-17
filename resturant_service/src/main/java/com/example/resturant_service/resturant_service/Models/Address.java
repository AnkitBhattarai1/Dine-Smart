package com.example.resturant_service.resturant_service.Models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    String province;
    String district;
    String city;
    String detailedPlace;

}
