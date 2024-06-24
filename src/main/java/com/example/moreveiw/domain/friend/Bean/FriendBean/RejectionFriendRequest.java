package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendRequestRejectionSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByEmailSmallBean;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RejectionFriendRequest {
    private final FriendRequestRejectionSmallBean friendRequestRejectionSmallBean;
    private final UserGetByEmailSmallBean userGetByEmailSmallBean;

    public StateResponseDTO exec(FriendRequestDTO friendRequestDTO) {
        return friendRequestRejectionSmallBean.exec(friendRequestDTO.getMemberId(), userGetByEmailSmallBean.exec(friendRequestDTO.getFriendEmail()).getId());
    }
}
