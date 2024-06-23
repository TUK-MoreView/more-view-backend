package com.example.moreveiw.domain.friend.Service;

import com.example.moreveiw.domain.friend.Bean.FriendBean.*;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendListResponseDTO;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendRequestListResponseDTO;
import com.example.moreveiw.domain.friend.Model.ENUM.FriendSort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import java.util.List;



@RequiredArgsConstructor
@Service
public class FriendService {

    private final SendFriendRequest friendRequestBean;
    private final GetSentFriendRequests friendRequestListBean;
    private final GetReceivedFriendRequests friendRequestedListBean;
    private final DeleteFriendRequest friendRequestDeleteBean;
    private final RejectionFriendRequest friendRequestRejectionBean;
    private final AcceptFriendRequest friendRequestAcceptBean;
    private final DeleteFriend friendDeleteBean;
    private final GetFriends friendListBean;

    @Transactional
    public StateResponseDTO requestFriend(FriendRequestDTO friendRequestDTO) {
        return friendRequestBean.exec(friendRequestDTO);
    }

    @Transactional(readOnly = true)
    public List<FriendRequestListResponseDTO> requestList(Long memberId, Integer page, Integer size) {
        return friendRequestListBean.exec(memberId, page, size);
    }

    @Transactional(readOnly = true)
    public List<FriendRequestListResponseDTO> requestedList(Long memberId, Integer page, Integer size) {
        return friendRequestedListBean.exec(memberId, page, size);
    }

    @Transactional
    public StateResponseDTO deleteRequest(FriendRequestDTO friendRequestDTO) {
        return friendRequestDeleteBean.exec(friendRequestDTO);
    }

    @Transactional
    public StateResponseDTO rejectFriend(FriendRequestDTO friendRequestDTO) {
        return friendRequestRejectionBean.exec(friendRequestDTO);
    }

    @Transactional
    public StateResponseDTO acceptFriend(FriendRequestDTO friendRequestDTO) {
        return friendRequestAcceptBean.exec(friendRequestDTO);
    }

    @Transactional
    public StateResponseDTO deleteFriend(FriendRequestDTO friendRequestDTO) {
        return friendDeleteBean.exec(friendRequestDTO);
    }

    @Transactional(readOnly = true)
    public List<FriendListResponseDTO> friendList(Long memberId, Integer page, Integer size, FriendSort sort) {
        return friendListBean.exec(memberId, page, size, sort);
    }
}
