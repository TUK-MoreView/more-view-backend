package com.example.moreveiw.domain.image.repository;

import com.example.moreveiw.domain.image.model.dao.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
