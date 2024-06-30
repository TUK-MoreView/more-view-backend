package com.example.moreveiw.domain.project.model.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProjectPaging(List<ProjectSingleResponse> projects, Integer totalPage,
                            Long totalElements, Boolean isFirst, Boolean isLast) {
}
