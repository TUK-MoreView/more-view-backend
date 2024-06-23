package com.example.moreveiw.domain.friend.Model.DTO.Friend.Response;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FriendListResponseDTO {
    private Long friendId;
    private String friendEmail;
    private String friendName;
    private String friendProfile;
    private LocalDateTime createdAt;
}