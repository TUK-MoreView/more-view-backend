package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DAO.FriendDAO;
import com.example.moreveiw.domain.friend.Repository.FriendRepository;
import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendSaveSmallBean {
    private final FriendRepository friendRepository;

    public void exec(Member member, Member friend) {
        FriendDAO friendDAO = FriendDAO.builder()
                .member(member)
                .friend(friend)
                .build();
        friendRepository.save(friendDAO);
    }
}
