package com.example.moreveiw.domain.threeDAI.repository;

import com.example.moreveiw.domain.threeDAI.model.dao.ThreeDAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreeDAIRepository extends JpaRepository<ThreeDAI, Long> {
    Optional<ThreeDAI> findByName(String name);
}
