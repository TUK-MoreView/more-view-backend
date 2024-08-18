package com.example.moreveiw.domain.member.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequest {

    @NotBlank
    @Email
    @Schema(description = "로그인 이메일", example = "moreview111@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호", example = "asdf1234!")
    private String password;
}
