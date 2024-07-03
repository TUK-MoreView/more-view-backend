package com.example.moreveiw.domain.threeD.repository;

import com.example.moreveiw.domain.threeD.model.dao.ThreeD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreeDRepository extends JpaRepository<ThreeD, Long> {
    //findByProjectId
     List<ThreeD> findByProjectId(Long projectId);
}
