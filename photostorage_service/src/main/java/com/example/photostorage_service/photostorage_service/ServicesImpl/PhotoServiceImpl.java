package com.example.photostorage_service.photostorage_service.ServicesImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.photostorage_service.photostorage_service.Constants.Constants;
import com.example.photostorage_service.photostorage_service.Models.Photo;
import com.example.photostorage_service.photostorage_service.Repo.PhotoRepo;
import com.example.photostorage_service.photostorage_service.Services.PhotoService;

import jakarta.transaction.Transactional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepo photoRepo;

    private enum PhotoType {
        MENUITEMS(Constants.getMenuItemsPhotosLocation(), "MENU_ITEMS_PHOTOS_"),
        USER(Constants.getUserPhotoLocation(), "USER_PHOTOS_"),
        DEFAULT(Constants.getDefaultLocation(), "DEFAULT_PHOTOS_");

        private String location;
        private String name;

        PhotoType(String location, String name) {
            this.location = location;
            this.name = name;
            // System.out.println(location);
        }

        private String getLocation() {
            return location;
        }

    }

    @Transactional
    @Override
    public String uploadPhoto(MultipartFile file, String photoOf) {
        /*
         * If not specified the photo will be stored in the default photo storage
         * locatio ... Need think and implement logic which photOf is discarded and how
         * .
         */

        PhotoType t = PhotoType.DEFAULT;

        for (PhotoType type : PhotoType.values()) {
            if (type.toString().equalsIgnoreCase(photoOf))
                t = type;
            System.out.println(photoOf);

        }

        // System.out.println(t);

        String result;

        switch (t) {
            case USER -> result = uploadPhoto.apply(t, file);
            case MENUITEMS -> result = uploadPhoto.apply(t, file);
            case DEFAULT -> result = uploadPhoto.apply(t, file);
            default -> throw new IllegalArgumentException("Unexpected value: " + t);
        }
        return result;
    }

    Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    BiFunction<PhotoType, MultipartFile, String> uploadPhoto = (photoType, image) -> {
        Photo p = new Photo();
        Photo savedInstance = photoRepo.save(p);
        /* Path where photo is upload in String */
        String photoStorageLocation = PhotoType.DEFAULT.location;
        switch (photoType) {
            case MENUITEMS -> photoStorageLocation = PhotoType.MENUITEMS.location;
            case USER -> photoStorageLocation = PhotoType.USER.location;
            case DEFAULT -> photoStorageLocation = PhotoType.DEFAULT.location;
            default -> throw new IllegalArgumentException();
        }

        // System.out.println(photoStorageLocation);
        /* Path where the photo is uploaded */
        Path storageLocation = Paths.get(photoStorageLocation).toAbsolutePath().normalize();

        try {
            if (!Files.exists(storageLocation))
                Files.createDirectories(storageLocation);

            Files.copy(image.getInputStream(),
                    storageLocation.resolve(savedInstance.getId() + fileExtension.apply(image.getOriginalFilename())));

            // System.out.println(photoType);

            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/photo/" + photoType.name +
                            savedInstance.getId() + fileExtension.apply(image.getOriginalFilename()))
                    .toUriString();
            p.setLocation(uri);
            photoRepo.save(p);

            return uri;

        } catch (Exception f) {

            throw new AssertionError(f);
        }

    };

    // BiFunction<String, MultipartFile, String> uploadUserPhoto = (id, image) -> {
    // try {

    // Path storageLocation =
    // Paths.get(constant.getUserPhotoLocation()).toAbsolutePath().normalize();

    // if (!Files.exists(storageLocation))
    // Files.createDirectories(storageLocation);

    // Files.copy(image.getInputStream(),
    // storageLocation.resolve(id +
    // fileExtension.apply(image.getOriginalFilename())));

    // return ServletUriComponentsBuilder.fromCurrentContextPath()
    // .path("/photo/" + id +
    // fileExtension.apply(image.getOriginalFilename())).toUriString();

    // } catch (Exception f) {

    // throw new AssertionError(f);
    // }
    // };

    // BiFunction<String, MultipartFile, String> uploadMenuItemsPhoto = (id, image)
    // -> {
    // try {

    // Path storageLocation =
    // Paths.get(constant.getMenuItemsPhotosLocation()).toAbsolutePath().normalize();

    // if (!Files.exists(storageLocation))

    // Files.createDirectories(storageLocation);

    // Files.copy(image.getInputStream(),
    // storageLocation.resolve(id +
    // fileExtension.apply(image.getOriginalFilename())));

    // return ServletUriComponentsBuilder.fromCurrentContextPath()
    // .path("/photo/" + id +
    // fileExtension.apply(image.getOriginalFilename())).toUriString();

    // } catch (Exception e) {
    // throw new AssertionError(e);
    // }
    // };

}
