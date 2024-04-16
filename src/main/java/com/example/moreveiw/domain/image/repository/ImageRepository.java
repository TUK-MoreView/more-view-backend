package com.example.moreveiw.domain.image.repository;

import com.example.moreveiw.domain.image.model.dao.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
