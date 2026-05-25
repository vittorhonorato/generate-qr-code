package com.vittorhonorato.generate_qr_code.controller;

import com.vittorhonorato.generate_qr_code.dto.QrCodeGenerateRequest;
import com.vittorhonorato.generate_qr_code.dto.QrCodeGenerateResponse;
import com.vittorhonorato.generate_qr_code.service.QrCodeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("qrcode")
@CrossOrigin("http://localhost:4200")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generateQrCode(
            @RequestBody @Valid QrCodeGenerateRequest request
            ) {

        try {
            QrCodeGenerateResponse response = qrCodeService.generate(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
