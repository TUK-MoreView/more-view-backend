package com.example.moreveiw.domain.member.service;

import com.example.moreveiw.domain.member.exception.NotFoundMemberException;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.model.dao.MemberMapper;
import com.example.moreveiw.domain.member.model.dto.response.MemberResponse;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import com.example.moreveiw.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    public MemberResponse getMyMemberWithAuthorities() {
        return memberMapper.toResponse(
                SecurityUtil.getCurrentUsername()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException("회원을 찾을 수 없습니다."))
        );
    }

    public Optional<Member> findByEmailOptional(final String email) {
        return memberRepository.findByEmail(email);
    }

    public String getCurrentMemberEmail() {
        return SecurityUtil.getCurrentUsername()
                .orElseThrow(() -> new NotFoundMemberException("해당 이메일의 회원을 찾을 수 없습니다."));
    }
}
