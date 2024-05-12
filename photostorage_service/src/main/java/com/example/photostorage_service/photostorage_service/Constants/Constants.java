package com.example.photostorage_service.photostorage_service.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;

@Component
public class Constants {

    // public enum storageLocation {

    // USER_PHOTO_LOCATION("");

    // public String location;

    // storageLocation(String location) {
    // this.location = location;
    // }
    // }

    private String location;

    private static String USER_PHOTO_LOCATION;
    private static String MENU_ITEMS_PHOTOS_LOCATION;
    private static String DEFAULT_LOCATION;

    private static String root = System.getProperty("user.home");

    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        location = env.getProperty("application.storage");
        USER_PHOTO_LOCATION = root + location + "USER_PHOTOS/";
        MENU_ITEMS_PHOTOS_LOCATION = root + location + "MENU_ITEMS_PHOTOS/";
        DEFAULT_LOCATION = root + location + "DEFAULT_PHOTOS/";
    }

    public static String getUserPhotoLocation() {
        return USER_PHOTO_LOCATION;
    }

    public static String getMenuItemsPhotosLocation() {
        return MENU_ITEMS_PHOTOS_LOCATION;
    }

    public static String getDefaultLocation() {
        return DEFAULT_LOCATION;
    }

    public String getlocation() {
        return (root + location);
    }
}
