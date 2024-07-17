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
    private Long threeDId;

    private Long projectId;

    private String id;

    private String textures;
    private String urls;
    private String extension;
    private String Name;
    private String bin;
    private String mtl;
    private String obj;
    private String gltf;

    private int x;
    private int y;
    private int z;

    private int size;
}
