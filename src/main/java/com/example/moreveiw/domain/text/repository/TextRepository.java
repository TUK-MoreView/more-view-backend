package com.example.moreveiw.domain.text.repository;

import com.example.moreveiw.domain.text.model.dao.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
}
