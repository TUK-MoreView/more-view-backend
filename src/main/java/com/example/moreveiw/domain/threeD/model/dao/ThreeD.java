package com.example.moreveiw.domain.threeD.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "3D")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreeD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String extension;
    private String idName;
    private String bin;
    private String mtl;
    private String obj;
    private String gltf;

}
