package com.example.menu_service.menu_service.dto;

public record Location(float latitude, float longitude) {

    public Location {

        if (latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("Invalid latitude");

        if (longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Invalid longitude");
    }

    public double distanceTo(Location other) {
        return Math.sqrt(latitude * latitude + longitude * longitude);
    }

}