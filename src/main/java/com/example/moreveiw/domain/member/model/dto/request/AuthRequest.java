package com.example.moreveiw.domain.member.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@AllArgsConstructor
public class AuthRequest {

    @Email
    private String email;
    private String name;
    private String password;
}
