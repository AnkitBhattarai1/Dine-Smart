package com.example.photostorage_service.photostorage_service.ServicesImpl;

import java.io.IOException;
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

    @Autowired
    Constants constant;

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
    }

    @Transactional
    @Override
    public Photo uploadPhoto(MultipartFile file, String photoOf) {
        /*
         * If not specified the photo will be stored in the default photo storage
         * location
         * Need think and implement logic which photOf is discarded and how
         * .
         */

        PhotoType t = PhotoType.DEFAULT;

        for (PhotoType type : PhotoType.values()) {
            if (type.toString().equalsIgnoreCase(photoOf))
                t = type;
            System.out.println(photoOf);
        }
        // System.out.println(t);
        Photo result;

        switch (t) {
            case USER -> result = uploadPhoto.apply(t, file);
            case MENUITEMS -> result = uploadPhoto.apply(t, file);
            case DEFAULT -> result = uploadPhoto.apply(t, file);
            default -> throw new IllegalArgumentException("Unexpected value: " + t);
        }
        return result;
    }

    @Override
    public byte[] getPhoto(String filename) {
        String path = constant.getlocation() + filename.substring(0, filename.lastIndexOf("_")) + "/"
                + filename.substring(filename.lastIndexOf("_") + 1, filename.length());
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new byte[] {};// blank photo
        }
    }

    Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    BiFunction<PhotoType, MultipartFile, Photo> uploadPhoto = (photoType, image) -> {
        Photo p = new Photo();
        p.setPhotoType(photoType.toString());

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

            return p;
        } catch (Exception f) {

            throw new AssertionError(f);
        }
    };

    @Override
    public Photo PhotogetPhotoWithId(String id) {
        return photoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Photo Not Found"));
    }

}
