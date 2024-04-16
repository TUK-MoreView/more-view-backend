package com.example.moreveiw.domain.text.service;

import com.example.moreveiw.domain.text.model.dao.Text;
import com.example.moreveiw.domain.text.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextService {
    private final TextRepository textRepository;

    public Text exec(Text text) {
        return textRepository.save(text);
    }
}
