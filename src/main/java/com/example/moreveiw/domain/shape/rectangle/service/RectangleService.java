package com.example.moreveiw.domain.shape.rectangle.service;

import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import com.example.moreveiw.domain.shape.rectangle.repository.RectangleRepository;
import com.example.moreveiw.domain.text.model.dao.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RectangleService {

    private final RectangleRepository rectangleRepository;

    public Rectangle exec(Rectangle rectangle) {
        return rectangleRepository.save(rectangle);
    }
}
