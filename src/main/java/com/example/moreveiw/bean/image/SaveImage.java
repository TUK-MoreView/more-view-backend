package com.example.moreveiw.bean.image;

import com.example.moreveiw.entitiy.dao.ImageDAO;
import com.example.moreveiw.repositroy.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveImage {
    private final ImageRepository imageRepository;

    public ImageDAO exec(ImageDAO image) {
        return imageRepository.save(image);
    }
}
