package com.example.resturant_service.resturant_service.Services;

import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.resturant_service.resturant_service.Models.Tables;
import com.google.zxing.WriterException;

/**
 * TableService
 */
@Service
public interface TableService {

    public Tables createNewTables(String resturantId, int number);

    public byte[] crateqrcode(String tableId) throws WriterException, IOException;
}
