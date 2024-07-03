package com.example.moreveiw.domain.project.controller;

import com.example.moreveiw.domain.project.model.dao.Project;
import com.example.moreveiw.domain.project.model.dto.response.ProjectPaging;
import com.example.moreveiw.domain.project.model.dto.response.ProjectSingleResponse;
import com.example.moreveiw.domain.project.model.dto.response.ObjectResponse;
import com.example.moreveiw.domain.project.service.ProjectService;
import com.example.moreveiw.domain.project.service.ProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    private final ProjectsService projectService;
    private final ProjectService projectService;

    @Operation(summary = "프로젝트 목록", description = "내가 생성한 프로젝트 목록 조회하기")
    @GetMapping("/project/{memberId}")
    public ResponseEntity<ProjectPaging> getProjectList(@Parameter(description = "페이지 번호 (0부터 시작, 기본값 0)", example = "0")
                                                        @PathVariable(value = "memberId") Long memberId,
                                                        @RequestParam(value = "page", required = false, defaultValue = "0") final int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Project> projectPage = projectService.getProjectList(memberId, pageable);

        ProjectPaging projectPaging = ProjectPaging.builder()
                .projects(projectPage.getContent().stream()
                        .map(project -> ProjectSingleResponse.builder()
                                .projectId(project.getId())
                                .name(project.getName())
                                .roomId(project.getRoomId().toString())
                                .thumbnailUrl(project.getThumbnailUrl())
                                .createdAt(project.getCreatedAt())
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
    @GetMapping("/project0/{projectId}")
    public ObjectResponse requestList(@PathVariable(value = "projectId") Long projectId) {
        return projectService.getProjectByObject(projectId);
    }
}
