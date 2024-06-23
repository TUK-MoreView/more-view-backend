package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DAO.FriendRequestDAO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.friend.Repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendRequestSmallBean {
    private final FriendRequestRepository friendRequestRepository;


    public StateResponseDTO exec(Long memberId,Long friendId) {
        FriendRequestDAO friendRequestDAO = FriendRequestDAO.builder()
                .memberId(memberId)
                .friendId(friendId)
                .build();
        friendRequestRepository.save(friendRequestDAO);
        return StateResponseDTO.builder()
                .state(true)
                .build();
    }
}
