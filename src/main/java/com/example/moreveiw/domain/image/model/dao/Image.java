package com.example.moreveiw.domain.image.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private Long projectId;
    private Long pageId;

    private String crudType;
    private String id;
    private String imageUri;
    private int x;
    private int y;

    private int width;
    private int height;

}
