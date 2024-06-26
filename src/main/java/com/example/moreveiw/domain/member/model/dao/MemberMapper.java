package com.example.moreveiw.domain.member.model.dao;

import com.example.moreveiw.domain.member.model.dto.request.MemberRequest;
import com.example.moreveiw.domain.member.model.dto.response.MemberListResponse;
import com.example.moreveiw.domain.member.model.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member toEntity(MemberRequest request) {

        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return member;
    }

    public MemberResponse toResponse(Member member) {
        if (member == null) return null;

        return MemberResponse.builder()
                .email(member.getEmail())
                .role(member.getRole())
                .name(member.getName())
                .build();
    }

    public MemberListResponse toListResponse(List<Member> memberList) {
        List<MemberResponse> memberResponseList =
                memberList.stream().map(this::toResponse).collect(Collectors.toList());
        return MemberListResponse.builder()
                .memberList(memberResponseList)
                .build();
    }
}
