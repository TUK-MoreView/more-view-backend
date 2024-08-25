package com.example.moreveiw.domain.threeD.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "3D")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThreeD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threeDId;

    private Long projectId;

    private Long pageId;

    private String id;

    private String crudType;
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
