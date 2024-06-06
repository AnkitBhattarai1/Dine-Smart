package com.example.photostorage_service.photostorage_service.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.photostorage_service.photostorage_service.Models.Photo;

@Service
public interface PhotoService {

    // public String uploadUserPhoto(MultipartFile file);

    // public String uploadMenuItemPhoto(MultipartFile file);

    // public String uploadDefaultPhoto(MultipartFile file);

    public Photo uploadPhoto(MultipartFile file, String photoOf);

    public byte[] getPhoto(String filename);

    public Photo PhotogetPhotoWithId(String id);

}
