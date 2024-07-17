package com.example.moreveiw.domain.member.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthLoginRequest {

    private String username;
    private String password;
}
