package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendRequestSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByEmailSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.friend.Controller.ExceptionControll.InvalidException;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.friend.Repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendFriendRequest {

    private final FriendRequestSmallBean friendRequestSmallBean;
    private final UserGetByEmailSmallBean userGetByEmailSmallBean;
    private final UserGetByIdSmallBean userGetByIdSmallBean;
    private final FriendRepository friendRepository;

    public StateResponseDTO exec(FriendRequestDTO friendRequestDTO) {
        Long friendId = userGetByEmailSmallBean.exec(friendRequestDTO.getFriendEmail()).getId();
        //이미 친구면 에러
        if (friendRepository.existsByMemberAndFriend(userGetByIdSmallBean.exec(friendRequestDTO.getMemberId()), userGetByIdSmallBean.exec(friendId))) {
            throw new InvalidException("이미 친구 상태입니다.");
        }
        return friendRequestSmallBean.exec(friendRequestDTO.getMemberId(),friendId);
    }
}
