package com.example.moreveiw.domain.project.service;

import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.member.model.dao.Member;
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
    private final UserGetByIdSmallBean userGetByIdSmallBean;

    public Page<Project> getProjectList(Long memberId, Pageable pageable) {
        Member member = userGetByIdSmallBean.exec(memberId);
        return projectRepository.findByMember(member, pageable);
    }

}
