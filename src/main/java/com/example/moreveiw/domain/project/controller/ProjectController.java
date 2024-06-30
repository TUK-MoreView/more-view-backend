package com.example.moreveiw.domain.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Project Controller", description = "프로젝트 관련 API입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    @Operation(summary = "프로젝트 목록", description = "내가 생성한 프로젝트 목록 조회하기")
    @GetMapping("/project")
    public ResponseEntity<?> getProjectList() {
        return null;
    }

}
