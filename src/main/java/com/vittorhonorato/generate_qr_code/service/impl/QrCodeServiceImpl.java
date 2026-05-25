package com.vittorhonorato.generate_qr_code.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.vittorhonorato.generate_qr_code.dto.QrCodeGenerateResponse;
import com.vittorhonorato.generate_qr_code.service.QrCodeService;
import com.vittorhonorato.generate_qr_code.service.StorageService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private final StorageService storageService;
    private static final String IMAGE_PNG = "image/png";
    private  static final String PNG = "PNG";

    public QrCodeServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public QrCodeGenerateResponse generate(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(
                text,
                BarcodeFormat.QR_CODE,
                200,
                200
        );

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, PNG, pngOutputStream);
        byte[] pngQrCodeData = pngOutputStream.toByteArray();

        String url = storageService.upload(
                pngQrCodeData,
                UUID.randomUUID().toString(),
                IMAGE_PNG
        );

        return new QrCodeGenerateResponse(url);
    }
}
