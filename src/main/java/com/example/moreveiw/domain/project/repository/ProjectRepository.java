package com.example.moreveiw.domain.project.repository;

import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.project.model.dao.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p JOIN ProjectLinkMember plm ON p.id = plm.project.id WHERE plm.member = :member")
    Page<Project> findByMember(Member member, Pageable pageable);

    Project findByRoomId(String roomId);
}
