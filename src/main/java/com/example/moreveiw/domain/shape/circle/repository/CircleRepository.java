package com.example.moreveiw.domain.shape.circle.repository;

import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CircleRepository extends CrudRepository<Circle, Long> {
    //findByProjectId
    List<Circle> findByProjectId(Long projectId);
}
