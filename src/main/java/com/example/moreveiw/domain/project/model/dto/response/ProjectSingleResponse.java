package com.example.moreveiw.domain.project.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Schema(description = "프로젝트 맴버", example = "프로젝트 맴버")
    private List<MemberDTO> members;

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MemberDTO {
        @Schema(description = "프로젝트 맴버 ID", example = "1")
        private Long memberId;

        @Schema(description = "프로젝트 맴버 이름", example = "게시글 이름 예시")
        private String name;

        @Schema(description = "프로젝트 맴버 이메일", example = "asd@gmail.com")
        private String email;
    }
}

