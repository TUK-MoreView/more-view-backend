package com.example.moreveiw.domain.project.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectSingleResponse {

    @Schema(description = "프로젝트 ID", example = "1")
    private Long projectId;

    @Schema(description = "방 ID", example = "hash 값")
    private String roomId;

    @Schema(description = "프로젝트 이름", example = "게시글 이름 예시")
    public String name;

    @Schema(description = "프로젝트 썸네일", example = "프로젝트 썸네일 링크")
    public String thumbnailUrl;

    @Schema(description = "프로젝트 생성 시간", example = "2024-06-29 10:00:00")
    private LocalDateTime createdAt;

}
