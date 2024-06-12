package com.example.moreveiw.domain.member.model.dto.response;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
public class MemberResponse {

    private String name;
    private String password;
    private String email;
    private String role;
}
