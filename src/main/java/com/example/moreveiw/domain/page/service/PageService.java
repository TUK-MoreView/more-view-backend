package com.example.moreveiw.domain.page.service;

import com.example.moreveiw.domain.page.model.dao.Page;
import com.example.moreveiw.domain.page.repository.PageRepository;
import com.example.moreveiw.domain.websocket.entitiy.APIMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageService {
    private final PageRepository pageRepository;

    public Page save2DPage(Page page) {
        return pageRepository.save(page);
    }

    public Page save3DPage(Page page) {
        return pageRepository.save(page);
    }

    public void delete2DPage(Page page) {
        pageRepository.deleteById(page.getPageDBId());
    }

    public void delete3DPage(Page page) {
        pageRepository.deleteById(page.getPageDBId());
    }

}

