package com.example.moreveiw.domain.member.model.dao;

import com.example.moreveiw.domain.base.BaseEntity;
import com.example.moreveiw.domain.friend.Model.DAO.FriendDAO;
import com.example.moreveiw.domain.project.model.dao.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String password;

    @Email
    private String email;

    private String role;

    @OneToMany(mappedBy = "member", cascade =CascadeType.ALL)
    private List<FriendDAO> members = new ArrayList<>();

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
    private List<FriendDAO> friends = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    @ManyToMany
    private Set<Authority> authorities;
}
