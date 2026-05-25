package com.vittorhonorato.generate_qr_code.service;

import com.google.zxing.WriterException;
import com.vittorhonorato.generate_qr_code.dto.QrCodeGenerateResponse;

import java.io.IOException;

public interface QrCodeService {
    QrCodeGenerateResponse generate(String text) throws WriterException, IOException;
}
