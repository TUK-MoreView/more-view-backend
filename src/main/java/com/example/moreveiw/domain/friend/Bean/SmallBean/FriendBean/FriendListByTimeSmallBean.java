package com.example.moreveiw.domain.friend.Bean.SmallBean.FriendBean;

import com.example.moreveiw.domain.friend.Model.DAO.FriendDAO;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendListResponseDTO;
import com.example.moreveiw.domain.friend.Repository.FriendRepository;
import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FriendListByTimeSmallBean {
    private final FriendRepository friendRepository;

    public List<FriendListResponseDTO> exec(Member member, Integer page, Integer size) {
        List<FriendDAO> friendDAOS = friendRepository.findByMemberOrderByCreatedAtDesc(member, PageRequest.of(page, size)).toList();

        return friendDAOS.stream().map(friendDAO -> FriendListResponseDTO.builder()
                .friendId(friendDAO.getFriend().getId())
                .friendEmail(friendDAO.getFriend().getEmail())
                .friendName(friendDAO.getFriend().getName())
                .createdAt(friendDAO.getCreatedAt())
                .build()).collect(Collectors.toList());
    }
}
