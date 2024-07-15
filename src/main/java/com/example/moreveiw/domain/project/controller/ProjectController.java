package com.example.moreveiw.domain.project.controller;

import com.example.moreveiw.domain.project.constant.ProjectResponseConstant;
import com.example.moreveiw.domain.project.model.dao.Project;
import com.example.moreveiw.domain.project.model.dto.request.PostProjectMemberRequest;
import com.example.moreveiw.domain.project.model.dto.request.ProjectCreateRequest;
import com.example.moreveiw.domain.project.model.dto.response.ObjectResponse;
import com.example.moreveiw.domain.project.model.dto.response.ProjectPaging;
import com.example.moreveiw.domain.project.model.dto.response.ProjectSingleResponse;
import com.example.moreveiw.domain.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Project Controller", description = "프로젝트 관련 API입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
public class ProjectController {

    private final static int PAGE_SIZE = 10;

    private final ProjectService projectService;

    @Operation(summary = "프로젝트 목록", description = "내가 생성한 프로젝트 목록 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로젝트 목록 조회 생성 성공",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = ProjectResponseConstant.getProjectList,
                                    description = "프로젝트 목록이 조회되었습니다."
                            )))})
    @GetMapping("/project")
    public ResponseEntity<ProjectPaging> getProjectList(@RequestParam(value = "page", required = false, defaultValue = "0") final int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Project> projectPage = projectService.getProjectListByUserEmail(userEmail, pageable);

        ProjectPaging projectPaging = ProjectPaging.builder()
                .projects(projectPage.getContent().stream()
                        .map(project -> ProjectSingleResponse.builder()
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
                                        .collect(Collectors.toList()
                                        ))
                                .build())
                        .collect(Collectors.toList()))
                .totalPage(projectPage.getTotalPages())
                .totalElements(projectPage.getTotalElements())
                .isFirst(projectPage.isFirst())
                .isLast(projectPage.isLast())
                .build();

        return ResponseEntity.ok(projectPaging);
    }


    @Operation(summary = "Get Project Objects")
    @GetMapping("/project/{projectId}")
    public ObjectResponse getProjectByObject(@PathVariable(value = "projectId") Long projectId) {
        return projectService.getProjectByObject(projectId);
    }

    @Operation(summary = "Post project")
    @PostMapping("/project")
    public ProjectSingleResponse postProject(@RequestBody ProjectCreateRequest projectCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();
        return projectService.postProject(userEmail, projectCreateRequest);
    }

    @Operation(summary = "Post project member")
    @PostMapping("/project/member")
    public ProjectSingleResponse postProjectMember(@RequestBody PostProjectMemberRequest postProjectMemberRequest) {
        return projectService.postProjectMember(postProjectMemberRequest);
    }
}
