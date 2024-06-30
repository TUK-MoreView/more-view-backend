package com.example.moreveiw.domain.project.service;

import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.service.MemberService;
import com.example.moreveiw.domain.project.model.dao.Project;
import com.example.moreveiw.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberService memberService;

    public Page<Project> getProjectList(Pageable pageable) {
        String currentEmail = memberService.getCurrentMemberEmail();
        Member currentMember = memberService.findByEmailOptional(currentEmail)
                .orElseThrow(() -> new RuntimeException("Member not found with email: " + currentEmail));
        return projectRepository.findByMember(currentMember, pageable);
    }

}
