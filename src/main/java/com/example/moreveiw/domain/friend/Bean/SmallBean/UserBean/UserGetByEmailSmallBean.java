package com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean;

import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGetByEmailSmallBean {

    private final MemberRepository memberRepository;

    public Member exec(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No member with email: " + email));
    }
}
