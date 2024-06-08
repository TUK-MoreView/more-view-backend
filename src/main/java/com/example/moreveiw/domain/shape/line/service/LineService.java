package com.example.moreveiw.domain.shape.line.service;

import com.example.moreveiw.domain.shape.line.model.dao.Line;
import com.example.moreveiw.domain.shape.line.repository.LineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    public Line exec(Line line) {
        return lineRepository.save(line);
    }

}
