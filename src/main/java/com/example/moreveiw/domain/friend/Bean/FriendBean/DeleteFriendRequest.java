package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendRequestDeleteSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByEmailSmallBean;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteFriendRequest {
    private final FriendRequestDeleteSmallBean friendRequestDeleteSmallBean;
    private final UserGetByEmailSmallBean userGetByEmailSmallBean;

    public StateResponseDTO exec(FriendRequestDTO friendRequestDTO) {
        return friendRequestDeleteSmallBean.exec(friendRequestDTO.getMemberId(), userGetByEmailSmallBean.exec(friendRequestDTO.getFriendEmail()).getId());
    }
}
