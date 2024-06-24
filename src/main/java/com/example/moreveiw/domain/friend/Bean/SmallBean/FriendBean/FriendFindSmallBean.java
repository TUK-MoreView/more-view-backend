package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DAO.FriendRequestDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.moreveiw.domain.friend.Repository.FriendRequestRepository;
import com.example.moreveiw.domain.friend.Controller.ExceptionControll.FailException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendFindSmallBean {
    private final FriendRequestRepository friendRequestRepository;

    public void exec(Long memberId, Long friendId) {
        List<FriendRequestDAO> friendRequestDAOS = friendRequestRepository.findByMemberIdAndFriendId(memberId, friendId);
        if(friendRequestDAOS.stream().anyMatch(friendRequestDAO -> friendRequestDAO.getState().equals("ACCEPTED"))) {
            throw new FailException("헤당 친구요청이 없습니다.");
        }
    }
}
