package com.vittorhonorato.generate_qr_code.service;

public interface StorageService {
    String upload(byte[] fileData, String fileName, String contentType);
}
