package com.example.moreveiw.domain.project.repository;

import com.example.moreveiw.domain.project.model.dao.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<project, Long> {
}
