package com.example.moreveiw.domain.shape.rectangle.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rectangle")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String x;
    private String y;

    private float width;
    private float height;

    private String color;

}
