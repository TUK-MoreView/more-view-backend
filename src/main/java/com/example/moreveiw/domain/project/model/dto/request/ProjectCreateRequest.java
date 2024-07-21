package com.example.moreveiw.domain.project.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateRequest {

    @Schema(description = "프로젝트 이름", example = "게시글 이름 예시")
    @NotEmpty
    @Size(max = 500)
    public String name;

    @Schema(description = "프로젝트 썸네일", example = "프로젝트 썸네일 링크")
    @NotEmpty
    @Size(max = 5000)
    public String thumbnailUrl;

    @Schema(description = "프로젝트 맴버", example = "프로젝트 맴버")
    @NotEmpty
    @Size(max = 5000)
    public Long memberId;

}
