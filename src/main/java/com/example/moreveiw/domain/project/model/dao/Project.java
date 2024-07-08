package com.example.moreveiw.domain.project.model.dao;

import com.example.moreveiw.domain.base.BaseEntity;
import com.example.moreveiw.domain.member.model.dao.Member;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "project")
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    private String name;

    private String roomId;

    private String thumbnailUrl;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectLinkMember> members = new ArrayList<>();


}
