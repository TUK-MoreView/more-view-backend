package com.example.moreveiw.domain.threeDAI.service;

import com.example.moreveiw.domain.S3.service.S3Service;
import com.example.moreveiw.domain.threeDAI.model.dao.ThreeDAI;
import com.example.moreveiw.domain.threeDAI.model.dto.ThreeDAIResponse;
import com.example.moreveiw.domain.threeDAI.repository.ThreeDAIRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@Service
@RequiredArgsConstructor
public class ThreeDAIService {
    private final ThreeDAIRepository threeDAIRepository;
    private final S3Service s3Service;
    private final RestTemplate restTemplate = new RestTemplate();

    public String makeThreeDAI(String name) throws IOException {
        // Prepare request
        String url = "http://34.207.250.239:8000/generate";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String body = "{\"inputText\": \"" + name + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Send request
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
        byte[] responseData = response.getBody();

        // Convert byte array to MultipartFile
        MultipartFile multipartFile = new ByteArrayMultipartFile("output.obj", "output.obj", "application/octet-stream", responseData);

        // Upload to S3
        String dataUrl = s3Service.uploadImage(multipartFile).getImageUrl();
        // Save to DB
        threeDAIRepository.save(ThreeDAI.builder()
                .name(name)
                .dataUri(dataUrl)
                .build());


        return dataUrl;
    }

    public ThreeDAIResponse getThreeDAI(String name) {
        ThreeDAI threeDAI = threeDAIRepository.findByName(name).get();
        ThreeDAIResponse threeDAIResponse = ThreeDAIResponse.builder().
                id(threeDAI.getId()).
                dataUri(threeDAI.getDataUri()).build();
        return threeDAIResponse;
    }

    @Getter
    @RequiredArgsConstructor
    private static class ByteArrayMultipartFile implements MultipartFile {

        private final String name;
        private final String originalFilename;
        private final String contentType;
        private final byte[] content;

        @Override
        public boolean isEmpty() {
            return content.length == 0;
        }

        @Override
        public long getSize() {
            return content.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return content;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(content);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            try (FileOutputStream fos = new FileOutputStream(dest)) {
                fos.write(content);
            }
        }
    }
}
