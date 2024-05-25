package com.example.photostorage_service.photostorage_service.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;

/**
 * The {@code Constants} class configures and provides access to directories for
 * photo storage.
 * It initializes paths for user photos, menu item photos, and default photos
 * using system and environment properties.
 *
 * This component relies on Spring's {@link Environment} to fetch configuration
 * values during initialization.
 * Static methods are provided to access these path configurations throughout
 * the application.
 */
@Component
public class Constants {

    private String location;

    private static String USER_PHOTO_LOCATION;
    private static String MENU_ITEMS_PHOTOS_LOCATION;
    private static String DEFAULT_LOCATION;

    private static String root = System.getProperty("user.home");

    @Autowired
    private Environment env;

    /**
     * Initializes the photo storage paths once the Spring context has fully
     * initialized the bean.
     * This method reads the base storage location from the application properties
     * and constructs
     * full paths for user, menu items, and default photos directories.
     */
    @PostConstruct
    private void init() {
        location = env.getProperty("application.storage");
        USER_PHOTO_LOCATION = root + location + "USER_PHOTOS/";
        MENU_ITEMS_PHOTOS_LOCATION = root + location + "MENU_ITEMS_PHOTOS/";
        DEFAULT_LOCATION = root + location + "DEFAULT_PHOTOS/";
    }

    /**
     * Returns the full path of the user photo storage directory.
     * 
     * @return The path where user photos are stored.
     */
    public static String getUserPhotoLocation() {
        return USER_PHOTO_LOCATION;
    }

    /**
     * Returns the full path of the menu items photo storage directory.
     * 
     * @return The path where menu items photos are stored.
     */
    public static String getMenuItemsPhotosLocation() {
        return MENU_ITEMS_PHOTOS_LOCATION;
    }

    /**
     * Returns the full path of the default photo storage directory.
     * 
     * @return The path where default photos are stored.
     */
    public static String getDefaultLocation() {
        return DEFAULT_LOCATION;
    }

    /**
     * Returns the base location for all photo storage, combined with the system's
     * user home directory.
     * 
     * @return The base path for all photo storage directories.
     */
    public String getlocation() {
        return (root + location);
    }

}
