package com.example.moreveiw.repositroy;

import com.example.moreveiw.entitiy.dao.ImageDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageDAO, Long> {
}
