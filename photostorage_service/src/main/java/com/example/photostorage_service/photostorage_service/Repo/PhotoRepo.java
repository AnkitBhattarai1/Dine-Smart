package com.example.photostorage_service.photostorage_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.photostorage_service.photostorage_service.Models.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, String> {

}
