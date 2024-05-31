package com.example.demo.service;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyMinioService {

    @Autowired
    private MinioClient minioClient;

}