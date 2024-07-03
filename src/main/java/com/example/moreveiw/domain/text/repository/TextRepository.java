package com.example.moreveiw.domain.text.repository;

import com.example.moreveiw.domain.text.model.dao.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    //findByProjectId
    List<Text> findByProjectId(Long projectId);
}
