package com.example.moreveiw.domain.member.service;

import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.model.dto.request.AuthRequest;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup(AuthRequest request) {

        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        Boolean isExist = memberRepository.existsByName(name);

        if (isExist) {

            return;

        }

        Member data = Member.builder()
                .name(name)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_USER")
                .build();

        memberRepository.save(data);
    }
}
