package com.example.moreveiw.domain.threeDAI.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ThreeDAIResponse {
    private Long id;
    private String dataUri;
}
