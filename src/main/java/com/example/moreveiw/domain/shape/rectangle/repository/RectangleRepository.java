package com.example.moreveiw.domain.shape.rectangle.repository;

import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
    //findByProjectId
    List<Rectangle> findByProjectId(Long projectId);
}
