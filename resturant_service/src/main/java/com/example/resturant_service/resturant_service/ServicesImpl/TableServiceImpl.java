package com.example.resturant_service.resturant_service.ServicesImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.resturant_service.resturant_service.Exceptions.ResourceNotFoundException;
import com.example.resturant_service.resturant_service.Models.Resturant;
import com.example.resturant_service.resturant_service.Models.Tables;
import com.example.resturant_service.resturant_service.Repo.TableRepo;
import com.example.resturant_service.resturant_service.Services.ResturantService;
import com.example.resturant_service.resturant_service.Services.TableService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepo tableRepo;
    private final ResturantService resutrantService;
    private AtomicInteger i = new AtomicInteger(0);

    public TableServiceImpl(TableRepo tableRepo, ResturantService resturantService) {
        this.resutrantService = resturantService;
        this.tableRepo = tableRepo;
    }

    @Override
    public Tables createNewTables(String resturantId, int number) {
        Optional<Integer> maxTableNoOpt = tableRepo.findMaxTableNo();
        int newTableNo = maxTableNoOpt.map(maxTableNo -> maxTableNo + 1).orElse(1);

        Tables table = new Tables();
        Resturant resturant = resutrantService.getResturantWithId(resturantId);
        table.setResturant(resturant);
        table.setCapacity(number);
        table.setTableNo(newTableNo);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/order?resturantId=" + resturantId + "&tableId=" + table.getTableNo()).build().toUriString();
        table.setQr(url);
        return tableRepo.save(table);
    }

    @Override
    public byte[] crateqrcode(String tableId) throws WriterException, IOException {

        Tables t = tableRepo.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find the table"));

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(t.getQr(), BarcodeFormat.QR_CODE, 300, 350, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

}
