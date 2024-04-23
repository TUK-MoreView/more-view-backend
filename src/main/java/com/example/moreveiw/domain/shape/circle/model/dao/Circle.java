package com.example.moreveiw.domain.shape.circle.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "circle")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String x;
    private String y;

    private Long radiusX;
    private Long radiusY;

    private String color;

}
