package com.example.moreveiw.domain.member.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
