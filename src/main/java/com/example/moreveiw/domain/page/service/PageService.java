package com.example.moreveiw.domain.page.service;

import com.example.moreveiw.domain.page.model.dao.Page;
import com.example.moreveiw.domain.page.repository.PageRepository;
import com.example.moreveiw.domain.websocket.entitiy.APIMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageService {
    private final PageRepository imageRepository;

    public Page saveImage(Page image) {
        return imageRepository.save(image);
    }

    public void deleteImage(Page image) {
        imageRepository.deleteById(image.getPageDBId());
    }

}

