package com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean;

import com.example.moreveiw.domain.friend.Controller.ExceptionControll.InvalidException;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGetByEmailSmallBean {
    private final MemberRepository memberRepository;

    public Member exec(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (!member.isPresent()) {
            throw new InvalidException("가입되지 않은 이메일입니다.");
        }
        return member.get();
    }
}
