package com.example.moreveiw.domain.project.repository;

import com.example.moreveiw.domain.project.model.dao.ProjectLinkMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectLinkMemberRepository extends JpaRepository<ProjectLinkMember, Long> {
}
