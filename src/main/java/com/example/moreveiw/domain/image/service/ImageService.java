package com.example.moreveiw.domain.image.service;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public void deleteImage(Image image) {
        imageRepository.deleteById(image.getId());
    }
}
