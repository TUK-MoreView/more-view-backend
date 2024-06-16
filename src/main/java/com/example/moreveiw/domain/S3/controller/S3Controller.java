package com.example.moreveiw.domain.S3.controller;

import com.example.moreveiw.domain.S3.model.dto.S3UrlResponse;
import com.example.moreveiw.domain.S3.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "S3 Controller", description = "S3 관련 API")
public class S3Controller {
    private final S3Service imageService;

    @PostMapping(value = "/file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "이미지 업로드", description = "S3에 이미지를 업로드합니다.")
    public S3UrlResponse uploadImage(@RequestBody MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    @DeleteMapping("/file")
    @Operation(summary = "이미지 삭제", description = "S3에서 이미지를 삭제합니다.")
    public boolean deleteImage(@RequestParam String fileUrl) {
        return imageService.deleteImage(fileUrl);
    }
}
