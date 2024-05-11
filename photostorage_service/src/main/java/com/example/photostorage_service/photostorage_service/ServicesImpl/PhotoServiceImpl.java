package com.example.photostorage_service.photostorage_service.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.photostorage_service.photostorage_service.Repo.PhotoRepo;
import com.example.photostorage_service.photostorage_service.Services.PhotoService;

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepo photoRepo;

    @Override
    public String uploadPhoto(String id, MultipartFile file) {
        return null;

    }
}
