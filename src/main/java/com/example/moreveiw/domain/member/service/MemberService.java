//package com.moreview.domain.member.service;
//
//import lombok.RequiredArgsConstructor;
//import com.moreview.domain.member.Member;
//import com.moreview.domain.member.dto.request.AddMemberRequest;
//import com.moreview.domain.member.repository.MemberRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//    public Long save(AddMemberRequest dto) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        return memberRepository.save(Member.builder()
//                .email(dto.getEmail())
//                .password(encoder.encode(dto.getPassword()))
//                .build()).getId();
//    }
//
//    public Member findById(Long userId) {
//        return memberRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
//    }
//
//    public Member findByEmail(String email) {
//        return memberRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
//    }
//}
