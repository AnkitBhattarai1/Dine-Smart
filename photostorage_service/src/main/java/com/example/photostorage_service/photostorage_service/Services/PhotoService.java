package com.example.photostorage_service.photostorage_service.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PhotoService {

    // public String uploadUserPhoto(MultipartFile file);

    // public String uploadMenuItemPhoto(MultipartFile file);

    // public String uploadDefaultPhoto(MultipartFile file);

    public String uploadPhoto(MultipartFile file, String photoOf);

}
