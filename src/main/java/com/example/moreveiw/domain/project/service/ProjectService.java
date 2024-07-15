package com.example.moreveiw.domain.project.service;

import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByEmailSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.image.repository.ImageRepository;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.project.model.dao.Project;
import com.example.moreveiw.domain.project.model.dao.ProjectLinkMember;
import com.example.moreveiw.domain.project.model.dto.request.PostProjectMemberRequest;
import com.example.moreveiw.domain.project.model.dto.request.ProjectCreateRequest;
import com.example.moreveiw.domain.project.model.dto.response.ObjectResponse;
import com.example.moreveiw.domain.project.model.dto.response.ProjectSingleResponse;
import com.example.moreveiw.domain.project.repository.ProjectLinkMemberRepository;
import com.example.moreveiw.domain.project.repository.ProjectRepository;
import com.example.moreveiw.domain.shape.circle.repository.CircleRepository;
import com.example.moreveiw.domain.shape.line.repository.LineRepository;
import com.example.moreveiw.domain.shape.rectangle.repository.RectangleRepository;
import com.example.moreveiw.domain.text.repository.TextRepository;
import com.example.moreveiw.domain.threeD.repository.ThreeDRepository;
import com.example.moreveiw.domain.websocket.service.WebsocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserGetByIdSmallBean userGetByIdSmallBean;
    private final ImageRepository imageRepository;
    private final CircleRepository circleRepository;
    private final LineRepository lineRepository;
    private final RectangleRepository rectangleRepository;
    private final TextRepository textRepository;
    private final ThreeDRepository threeDRepository;
    private final WebsocketService websocketService;

    private final ProjectLinkMemberRepository projectLinkMemberRepository;
    private final UserGetByEmailSmallBean userGetByEmailSmallBean;


    @Transactional
    public ObjectResponse getProjectByObject(Long projectId) {
        return ObjectResponse.builder()
                .images(imageRepository.findByProjectId(projectId))
                .circles(circleRepository.findByProjectId(projectId))
                .lines(lineRepository.findByProjectId(projectId))
                .rectangles(rectangleRepository.findByProjectId(projectId))
                .texts(textRepository.findByProjectId(projectId))
                .threeDs(threeDRepository.findByProjectId(projectId))
                .build();
    }

    public Page<Project> getProjectList(Long memberId, Pageable pageable) {
        Member member = userGetByIdSmallBean.exec(memberId);
        return projectRepository.findByMember(member, pageable);
    }

    public Page<Project> getProjectListByUserEmail(String email, Pageable pageable) {
        Member member = userGetByEmailSmallBean.exec(email);
        return projectRepository.findByMember(member, pageable);
    }

    @Transactional
    public ProjectSingleResponse postProject(String email, ProjectCreateRequest projectCreateRequest) {
        Member member = userGetByEmailSmallBean.exec(email);
        Project project = new Project();
        project.setName(projectCreateRequest.getName());
        project.setRoomId(websocketService.createProjectRoom().getRoomId());
        project.setThumbnailUrl(projectCreateRequest.getThumbnailUrl());
        ProjectLinkMember projectLinkMember = new ProjectLinkMember();
        projectLinkMember.setMember(member);
        projectLinkMember.setProject(project);
        projectLinkMemberRepository.save(projectLinkMember);
        projectRepository.save(project);
        return ProjectSingleResponse.builder()
                .projectId(project.getId())
                .name(project.getName())
                .roomId(project.getRoomId().toString())
                .thumbnailUrl(project.getThumbnailUrl())
                .createdAt(project.getCreatedAt())
                .members(List.of(ProjectSingleResponse.MemberDTO.builder()
                        .memberId(member.getId())
                        .name(member.getName())
                        .email(member.getEmail())
                        .build()))
                .build();
    }

    @Transactional
    public ProjectSingleResponse postProjectMember(PostProjectMemberRequest postProjectMemberRequest) {
        Project project = projectRepository.findByRoomId(postProjectMemberRequest.getRoomId());
        ProjectLinkMember projectLinkMember = new ProjectLinkMember();
        projectLinkMember.setMember(userGetByIdSmallBean.exec(postProjectMemberRequest.getMemberId()));
        projectLinkMember.setProject(project);
        projectLinkMemberRepository.save(projectLinkMember);
        return ProjectSingleResponse.builder()
                .projectId(project.getId())
                .name(project.getName())
                .roomId(project.getRoomId().toString())
                .thumbnailUrl(project.getThumbnailUrl())
                .createdAt(project.getCreatedAt())
                .members(project.getMembers().stream()
                        .map(member -> ProjectSingleResponse.MemberDTO.builder()
                                .memberId(member.getMember().getId())
                                .name(member.getMember().getName())
                                .email(member.getMember().getEmail())
                                .build())
                        .toList())
                .build();
    }
}
