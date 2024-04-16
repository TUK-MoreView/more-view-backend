package com.example.moreveiw.global.security.oauth2.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {

    private String role;
    private String name;
    private String memberName;
}
