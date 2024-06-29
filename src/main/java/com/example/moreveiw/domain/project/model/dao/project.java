package com.example.moreveiw.domain.project.model.dao;

import com.example.moreveiw.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    private String name;

    private Long roomId;

    private String thumbnailUrl;

}
