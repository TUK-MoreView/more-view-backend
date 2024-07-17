package com.example.moreveiw.domain.member.service;

import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.model.dto.CustomUserDetails;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Member userData = memberRepository.findByName(name);

        if (userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}