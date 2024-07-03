package com.example.moreveiw.domain.shape.line.repository;

import com.example.moreveiw.domain.shape.line.model.dao.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    //findByProjectId
    List<Line> findByProjectId(Long projectId);
}
