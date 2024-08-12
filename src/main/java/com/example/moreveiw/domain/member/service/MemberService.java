package com.example.moreveiw.domain.member.service;

import com.example.moreveiw.domain.member.exception.DuplicateMemberException;
import com.example.moreveiw.domain.member.exception.NotFoundMemberException;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.model.dao.MemberMapper;
import com.example.moreveiw.domain.member.model.dto.request.MemberRequest;
import com.example.moreveiw.domain.member.model.dto.response.MemberResponse;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import com.example.moreveiw.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public MemberResponse signup(MemberRequest request) {
        if (memberRepository.findOneWithAuthoritiesByEmail(request.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 회원입니다.");
        }

        Member member = memberMapper.toEntity(request);

        return memberMapper.toResponse(memberRepository.save(member));
    }

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

    public Long findMemberIdByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No member found with email: " + email));
        return member.getId();
    }

}
