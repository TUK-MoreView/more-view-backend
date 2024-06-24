package com.example.moreveiw.domain.friend.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.moreveiw.domain.friend.Model.DAO.FriendRequestDAO;
import com.example.moreveiw.domain.friend.Model.ENUM.FriendRequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequestDAO, Long> {
    Page<FriendRequestDAO> findByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);

    //state가 PENDING인 것만 가져오기 AND FriendID로 찾기
    Page<FriendRequestDAO> findByFriendIdAndStateOrderByCreatedAtDesc(Long memberId, FriendRequestState state, Pageable pageable);

    //deleteByUserIdAndFriendId
    void deleteByMemberIdAndFriendId(Long memberId, Long friendId);


    //FriendRequestDAO의 State를 REJECTED로 변경
    @Modifying
    @Query("UPDATE FriendRequestDAO fr SET fr.state = :state WHERE fr.memberId = :memberId AND fr.friendId = :friendId")
    void updateStateByMemberIdAndFriendId(@Param("memberId") Long memberId, @Param("friendId") Long friendId, @Param("state") FriendRequestState state);

    List<FriendRequestDAO> findByMemberIdAndFriendId(Long memberId, Long friendId);

}