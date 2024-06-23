package com.example.moreveiw.domain.friend.Repository;

import com.example.moreveiw.domain.friend.Model.DAO.FriendDAO;
import com.example.moreveiw.domain.member.model.dao.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendDAO, Long> {
    //deleteByUserAndFriend
    void deleteByMemberAndFriend(Member member, Member friend);

    //findByUserIdOrderByCreatedAtDesc
    Page<FriendDAO> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);

    boolean existsByMemberAndFriend(Member member, Member friend);

}

