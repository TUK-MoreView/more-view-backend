package com.example.moreveiw.domain.member.controller;

import com.example.moreveiw.domain.member.model.dto.request.AuthRequest;
import com.example.moreveiw.domain.member.model.dto.response.MemberResponse;
import com.example.moreveiw.domain.member.service.AuthService;
import com.example.moreveiw.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Member Controller", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "회원가입", description = "새로운 회원을 가입시킵니다.")
    public String signup(@RequestPart("email") String email,
                         @RequestPart("name") String name,
                         @RequestPart("password") String password) {
        AuthRequest request = new AuthRequest(email, name, password);
        authService.signup(request);
        return "회원가입 완료";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "유저 정보 조회", description = "로그인한 유저의 정보를 조회합니다.")
    public ResponseEntity<MemberResponse> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities());
    }
}
