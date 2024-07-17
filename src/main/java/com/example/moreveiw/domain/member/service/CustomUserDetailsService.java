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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member userData = memberRepository.findByUsername(username);

        if (userData == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomUserDetails(userData);
    }
}