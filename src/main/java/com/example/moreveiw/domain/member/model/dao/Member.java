package com.example.moreveiw.domain.member.model.dao;

import com.example.moreveiw.domain.base.BaseEntity;
import com.example.moreveiw.domain.friend.Model.DAO.FriendDAO;
import com.example.moreveiw.domain.project.model.dao.ProjectLinkMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;

    @Email
    private String email;

    private String role;

    @OneToMany(mappedBy = "member", cascade =CascadeType.ALL)
    private List<FriendDAO> members = new ArrayList<>();

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
    private List<FriendDAO> friends = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ProjectLinkMember> projects = new ArrayList<>();

    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    @ManyToMany
    private Set<Authority> authorities;
}
