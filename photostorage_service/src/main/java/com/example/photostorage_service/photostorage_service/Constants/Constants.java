package com.example.photostorage_service.photostorage_service.Constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    @Value("${application.storage}")
    private static String location;

    private static String root = System.getProperty("user.home");

    public static final String USER_PHOTO_LOCATION = root + location + "USER_PHOTOS";
    public static final String MENU_ITEMS_PHOTOS_LOCATION = root + location + "MENU_ITEMS_PHOTOS";
}
