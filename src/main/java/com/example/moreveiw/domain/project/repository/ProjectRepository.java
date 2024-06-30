package com.example.moreveiw.domain.project.repository;

import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.project.model.dao.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByMember(Member member, Pageable pageable);
}
