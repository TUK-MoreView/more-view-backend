package com.example.moreveiw.domain.friend.Model.DAO;

import com.example.moreveiw.domain.friend.Model.ENUM.FriendRequestState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "friend_request")
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class FriendRequestDAO {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long memberId;
        private Long friendId;

        @CreationTimestamp
        @Column(updatable = false)
        private LocalDateTime createdAt;

        @Builder.Default
        private FriendRequestState state = FriendRequestState.PENDING;
    }

