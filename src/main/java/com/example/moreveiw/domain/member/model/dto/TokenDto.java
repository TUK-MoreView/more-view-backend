package com.example.moreveiw.domain.member.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String token;
    private Long memberId;
    private String name;
    private String email;
}
