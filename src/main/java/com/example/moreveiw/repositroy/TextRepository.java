package com.example.moreveiw.repositroy;

import com.example.moreveiw.entitiy.dao.TextDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<TextDAO, Long> {
}
