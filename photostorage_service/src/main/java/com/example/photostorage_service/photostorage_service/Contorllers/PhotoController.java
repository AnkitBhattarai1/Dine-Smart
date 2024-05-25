package com.example.photostorage_service.photostorage_service.Contorllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.photostorage_service.photostorage_service.Constants.Constants;
import com.example.photostorage_service.photostorage_service.Models.Photo;
import com.example.photostorage_service.photostorage_service.Services.PhotoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * The {@code PhotoController} class handles HTTP requests for photo upload and
 * retrieval.
 * It provides endpoints for uploading photos and fetching them by filename,
 * supporting JPEG and PNG formats.
 *
 * This controller uses {@link PhotoService} for the business logic of photo
 * handling and
 * {@link Constants} for accessing the file storage paths.
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @Autowired
    Constants constant;

    /**
     * Handles a POST request to save a photo. This endpoint accepts a file and an
     * optional description
     * about the photo ('photoOf'). If 'photoOf' is not provided, it defaults to
     * "default".
     *
     * The photo is uploaded through a {@link MultipartFile} parameter.
     *
     * This method uses the {@link PhotoService} to persist the photo and returns a
     * {@link Photo} object
     * that contains metadata about the saved photo, such as its URL, storage
     * location, and description.
     *
     * @param file    A {@link MultipartFile} object containing the file data of the
     *                photo to be uploaded.
     *                The file should not be null or empty.
     * @param photoOf An optional String describing what the photo is of, enhancing
     *                searchability or
     *                categorization of photos stored. Defaults to "default" if not
     *                provided.
     * @return A {@link Photo} object representing the persisted photo along with
     *         its metadata.
     */

    @PostMapping("/save")
    public Photo uploadPhoto(@RequestParam(name = "filename") MultipartFile file,
            @RequestParam(name = "photoOf", defaultValue = "default") String photoOf) {

        // System.out.println(Constants.getUserPhotoLocation());
        System.out.println(photoOf);
        return photoService.uploadPhoto(file, photoOf);
    }

    /**
     * Handles a GET request to retrieve a photo based on its filename. This method
     * serves photos
     * directly as byte arrays and supports images in JPEG and PNG formats, which
     * are specified
     * in the 'produces' attribute of the {@link GetMapping} annotation.
     *
     * The method constructs the path from the filename, reads the file from the
     * storage, and returns
     * its byte array. This byte array is then directly rendered in the browser or
     * client application
     * that made the request. If the file does not exist or an error occurs during
     * file reading, an
     * appropriate exception is thrown.
     *
     * @param filename The filename from the URL, used to locate the photo in the
     *                 storage.
     * @return A byte array containing the image data, or an appropriate HTTP error
     *         if an issue occurs.
     * @throws IOException If there is an issue reading the file from the disk.
     */

    @GetMapping(path = "/{filename}", produces = { IMAGE_PNG_VALUE,
            IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        String path = constant.getlocation() + filename.substring(0, filename.lastIndexOf("_")) + "/"
                + filename.substring(filename.lastIndexOf("_") + 1, filename.length());
        return Files.readAllBytes(Paths.get(path));
    }

}
