package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;


import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.friend.Repository.FriendRepository;
import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendDeleteSmallBean {
    private final FriendRepository friendRepository;

    public StateResponseDTO exec(Member member, Member friendDAO) {
        friendRepository.deleteByMemberAndFriend(member, friendDAO);
        friendRepository.deleteByMemberAndFriend(friendDAO, member);
        return StateResponseDTO.builder().state(true).build();
    }
}
