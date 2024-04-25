package com.example.moreveiw.domain.shape.circle.service;

import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import com.example.moreveiw.domain.shape.circle.repository.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CircleService {

    private final CircleRepository circleRepository;

    public Circle exec(Circle circle) {
        return circleRepository.save(circle);
    }
}
