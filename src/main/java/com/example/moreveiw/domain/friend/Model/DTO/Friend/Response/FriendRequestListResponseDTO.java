package com.example.moreveiw.domain.friend.Model.DTO.Friend.Response;

import com.example.moreveiw.domain.friend.Model.ENUM.FriendRequestState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FriendRequestListResponseDTO {
    private Long friendId;
    private String friendEmail;
    private String friendName;
    private String friendProfile;
    private FriendRequestState friendStatus;
    private LocalDateTime createdAt;
    
}
