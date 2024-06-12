package com.example.moreveiw.domain.S3.controller;

import com.example.moreveiw.domain.S3.model.dto.S3UrlResponse;
import com.example.moreveiw.domain.S3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class S3Controller {
    private final S3Service imageService;

    @PostMapping(value = "/Image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public S3UrlResponse uploadImage(@RequestBody MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    @DeleteMapping("/Image")
    public boolean deleteImage(@RequestParam String fileUrl) {
        return imageService.deleteImage(fileUrl);
    }
}
