package com.example.moreveiw.domain.member.service;

import com.example.moreveiw.domain.member.editor.MemberEditor;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.model.dto.JoinDto;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public void joinProcess(JoinDto joinDto) {

        String memberName = joinDto.getMemberName();
        String password = joinDto.getPassword();

        Boolean isExist = memberRepository.existsByMemberName(memberName);

        if (isExist) {

            return;
        }

        MemberEditor memberEditor = MemberEditor.builder()
                .memberName(memberName)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_ADMIN")
                .build();

        memberRepository.save(Member.builder().build());
    }
}
