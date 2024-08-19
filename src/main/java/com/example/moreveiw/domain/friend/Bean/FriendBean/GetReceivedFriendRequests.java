package com.example.moreveiw.domain.friend.Bean.FriendBean;

import com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean.FriendRequestedListSmallBean;
import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.friend.Model.DAO.FriendRequestDAO;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendRequestListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetReceivedFriendRequests {
    private final FriendRequestedListSmallBean friendRequestedListSmallBean;
    private final UserGetByIdSmallBean userGetByIdSmallBean;

    public List<FriendRequestListResponseDTO> exec(Long userId, Integer page, Integer size) {
        List<FriendRequestDAO> friendRequestDAOS = friendRequestedListSmallBean.exec(userId, page, size);

        return friendRequestDAOS.stream()
                .map(friendRequestDAO -> FriendRequestListResponseDTO.builder()
                        .friendId(friendRequestDAO.getMemberId())
                        .friendEmail(userGetByIdSmallBean.exec(friendRequestDAO.getMemberId()).getEmail())
                        .friendName(userGetByIdSmallBean.exec(friendRequestDAO.getMemberId()).getName())
                        .friendStatus(friendRequestDAO.getState())
                        .createdAt(friendRequestDAO.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
