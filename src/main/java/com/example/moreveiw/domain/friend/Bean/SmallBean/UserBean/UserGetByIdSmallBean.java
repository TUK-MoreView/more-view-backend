package com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean;

import com.example.moreveiw.domain.friend.Controller.ExceptionControll.ResourceNotFoundException;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGetByIdSmallBean {

    private final MemberRepository memberRepository;

    public Member exec(Long memberId) {

        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()) {
             throw new ResourceNotFoundException("존재하지 않는 유저입니다.");
        }
        return member.get();
    }
}
