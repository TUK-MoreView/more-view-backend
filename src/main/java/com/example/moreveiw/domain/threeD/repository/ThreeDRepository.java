package com.example.moreveiw.domain.threeD.repository;

import com.example.moreveiw.domain.threeD.model.dao.ThreeD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreeDRepository extends JpaRepository<ThreeD, Long> {
}
