package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.friend.Repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendRequestDeleteSmallBean {
    private final FriendRequestRepository friendRequestRepository;

    public StateResponseDTO exec(Long memberId, Long friendId) {
        friendRequestRepository.deleteByMemberIdAndFriendId(memberId, friendId);
        return StateResponseDTO.builder().state(true).build();
    }
}
