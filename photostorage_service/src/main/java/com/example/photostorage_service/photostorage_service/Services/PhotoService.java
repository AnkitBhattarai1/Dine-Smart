package com.example.photostorage_service.photostorage_service.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PhotoService {

    public String uploadPhoto(String id, MultipartFile file);
}
