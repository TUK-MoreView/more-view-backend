package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.friend.Model.ENUM.FriendRequestState;
import com.example.moreveiw.domain.friend.Repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendRequestRejectionSmallBean {
    private final FriendRequestRepository friendRequestRepository;

    public StateResponseDTO exec(Long memberId,Long friendId) {
        //FriendRequestDAO의 State를 REJECTED로 변경
        friendRequestRepository.updateStateByMemberIdAndFriendId(friendId, memberId, FriendRequestState.REJECTED);

        return StateResponseDTO.builder().state(true).build();
    }
}
