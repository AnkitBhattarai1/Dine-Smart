package com.example.photostorage_service.photostorage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.CloudEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotostorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotostorageServiceApplication.class, args);
	}
}
