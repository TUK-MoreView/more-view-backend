package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendListByTimeSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendListResponseDTO;
import com.example.moreveiw.domain.friend.Model.ENUM.FriendSort;
import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFriends {
    private final UserGetByIdSmallBean userGetByIdSmallBean;
    private final FriendListByTimeSmallBean friendListByTimeSmallBean;

    public List<FriendListResponseDTO> exec(Long memberId, Integer page, Integer size, FriendSort sort) {
        Member member = userGetByIdSmallBean.exec(memberId);
        List<FriendListResponseDTO> friendListResponseDTOS;
        friendListResponseDTOS = friendListByTimeSmallBean.exec(member, page, size);

        return friendListResponseDTOS;
    }
}
