package com.example.moreveiw.bean;

import com.example.moreveiw.domain.ImageDAO;
import com.example.moreveiw.repositroy.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageSaveBean {
    private final ImageRepository imageRepository;

    public ImageDAO exec(ImageDAO image) {
        return imageRepository.save(image);
    }
}
