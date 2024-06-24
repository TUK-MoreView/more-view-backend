package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DAO.FriendRequestDAO;
import com.example.moreveiw.domain.friend.Model.ENUM.FriendRequestState;
import com.example.moreveiw.domain.friend.Repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendRequestedListSmallBean {
    private final FriendRequestRepository friendRequestRepository;

    public List<FriendRequestDAO> exec(Long memberId, Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        Page<FriendRequestDAO> friendRequestDAOS = friendRequestRepository.findByFriendIdAndStateOrderByCreatedAtDesc(memberId, FriendRequestState.PENDING,request);
        return friendRequestDAOS.toList();
    }
}
