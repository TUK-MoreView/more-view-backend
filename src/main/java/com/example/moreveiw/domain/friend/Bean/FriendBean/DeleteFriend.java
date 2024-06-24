package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendDeleteSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByEmailSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.member.editor.MemberEditor;
import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteFriend {
    private final FriendDeleteSmallBean friendDeleteSmallBean;
    private final UserGetByEmailSmallBean userGetByEmailSmallBean;
    private final UserGetByIdSmallBean userGetByIdSmallBean;

    public StateResponseDTO exec(FriendRequestDTO friendRequestDTO) {
        Member member = userGetByIdSmallBean.exec(friendRequestDTO.getMemberId());
        Member friendDAO = userGetByEmailSmallBean.exec(friendRequestDTO.getFriendEmail());
        return friendDeleteSmallBean.exec(member, friendDAO);
    }
}
