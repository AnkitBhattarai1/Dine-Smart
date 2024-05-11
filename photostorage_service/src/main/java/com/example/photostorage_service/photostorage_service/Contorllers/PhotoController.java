package com.example.photostorage_service.photostorage_service.Contorllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @PostMapping("/save")
    public String uploadPhoto(@RequestParam MultipartFile file) {
        return null;
    }

    // @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE,
    // IMAGE_JPEG_VALUE })
    // public byte[] getPhoto(@PathVariable("filename") String filename) throws
    // IOException {
    // return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    // }

}
