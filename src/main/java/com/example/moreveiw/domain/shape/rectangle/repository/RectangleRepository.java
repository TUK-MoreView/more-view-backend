package com.example.moreveiw.domain.shape.rectangle.repository;

import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
}
