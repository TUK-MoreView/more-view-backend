package com.example.moreveiw.bean;

import com.example.moreveiw.domain.ImageDAO;
import com.example.moreveiw.domain.TextDAO;
import com.example.moreveiw.repositroy.ImageRepository;
import com.example.moreveiw.repositroy.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextSaveBean {
    private final TextRepository textRepository;

    public TextDAO exec(TextDAO text) {
        return textRepository.save(text);
    }
}
