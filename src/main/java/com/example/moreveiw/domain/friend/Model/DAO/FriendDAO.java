package com.example.moreveiw.domain.friend.Model.DAO;

import com.example.moreveiw.domain.member.model.dao.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "friend")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Member friend;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}

