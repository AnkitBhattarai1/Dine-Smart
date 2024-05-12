package com.example.photostorage_service.photostorage_service.Contorllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.photostorage_service.photostorage_service.Constants.Constants;
import com.example.photostorage_service.photostorage_service.Services.PhotoService;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @Autowired
    Constants constant;

    @PostMapping("/save")
    public String uploadPhoto(@RequestParam(name = "filename") MultipartFile file,
            @RequestParam(name = "photoOf", defaultValue = "default") String photoOf) {

        // System.out.println(Constants.getUserPhotoLocation());
        System.out.println(photoOf);
        return photoService.uploadPhoto(file, photoOf);
    }

    @GetMapping(path = "/{filename}", produces = { IMAGE_PNG_VALUE,
            IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        String path = constant.getlocation() + filename.substring(0, filename.lastIndexOf("_")) + "/"
                + filename.substring(filename.lastIndexOf("_") + 1, filename.length());
        return Files.readAllBytes(Paths.get(path));
    }

}
