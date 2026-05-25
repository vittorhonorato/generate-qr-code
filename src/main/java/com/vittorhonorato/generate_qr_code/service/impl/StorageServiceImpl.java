package com.vittorhonorato.generate_qr_code.service.impl;

import com.vittorhonorato.generate_qr_code.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class StorageServiceImpl implements StorageService {

    private final S3Client s3Client;
    private final String bucketName;
    private final String endpoint;

    public StorageServiceImpl(
            S3Client s3Client,
            @Value("${aws.s3.bucket}") String bucketName,
            @Value("${aws.endpoint}") String endpoint
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.endpoint = endpoint;
    }

    @Override
    public String upload(byte[] fileData, String fileName, String contentType) {

        PutObjectRequest request = PutObjectRequest.builder()
                .key(fileName)
                .bucket(bucketName)
                .contentType(contentType)
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(fileData));

        return String.format("%s/%s/%s", endpoint, bucketName, fileName);
    }
}
