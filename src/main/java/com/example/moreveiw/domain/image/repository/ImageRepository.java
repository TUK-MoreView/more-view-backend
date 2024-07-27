package com.example.moreveiw.domain.image.repository;

import com.example.moreveiw.domain.image.model.dao.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // findByProjectId
    List<Image> findByProjectId(Long projectId);
}
