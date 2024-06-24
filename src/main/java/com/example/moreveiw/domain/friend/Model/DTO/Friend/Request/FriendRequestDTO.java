package com.example.moreveiw.domain.friend.Model.DTO.Friend.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendRequestDTO {
    private Long memberId;
    private String friendEmail;
}
