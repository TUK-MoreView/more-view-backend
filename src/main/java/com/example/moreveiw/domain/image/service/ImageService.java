package com.example.moreveiw.domain.image.service;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.image.repository.ImageRepository;
import com.example.moreveiw.domain.websocket.entitiy.APIMessage;
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
        System.out.println("image.getImageId() = " + image.getImageId());
        imageRepository.deleteById(image.getImageId());
    }

    public Image createImage(APIMessage message) {
        return Image.builder()
                .projectId(message.getImage().getProjectId())
                .id(message.getImage().getId())
                .imageUri(message.getImage().getImageUri())
                .x(message.getImage().getX())
                .y(message.getImage().getY())
                .width(message.getImage().getWidth())
                .height(message.getImage().getHeight())
                .build();
    }

    public Image createImageForDeletion(APIMessage message) {
        return Image.builder()
                .imageId(message.getImage().getImageId())
                .build();
    }
}

