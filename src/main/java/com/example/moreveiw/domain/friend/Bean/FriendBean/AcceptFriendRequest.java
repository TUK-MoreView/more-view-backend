package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendFindSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendRequestDeleteSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendSaveSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByEmailSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AcceptFriendRequest {
    private final FriendRequestDeleteSmallBean friendRequestDeleteSmallBean;
    private final UserGetByEmailSmallBean userGetByEmailSmallBean;
    private final UserGetByIdSmallBean userGetByIdSmallBean;
    private final FriendSaveSmallBean friendSaveSmallBean;
    private final FriendFindSmallBean friendFindSmallBean;

    public StateResponseDTO exec(FriendRequestDTO friendRequestDTO) {
        Member member = userGetByIdSmallBean.exec(friendRequestDTO.getMemberId());
        Member friend = userGetByEmailSmallBean.exec(friendRequestDTO.getFriendEmail());
        friendFindSmallBean.exec(friend.getId(), member.getId());
        friendRequestDeleteSmallBean.exec(friend.getId(), friendRequestDTO.getMemberId());
        friendRequestDeleteSmallBean.exec(friendRequestDTO.getMemberId(), friend.getId());
        friendSaveSmallBean.exec(member, friend);
        friendSaveSmallBean.exec(friend, member);

        return StateResponseDTO.builder().state(true).build();
    }
}
