package com.example.moreveiw.bean.text;

import com.example.moreveiw.entitiy.dao.TextDAO;
import com.example.moreveiw.repositroy.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveText {
    private final TextRepository textRepository;

    public TextDAO exec(TextDAO text) {
        return textRepository.save(text);
    }
}
