package com.example.moreveiw.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "text")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String text;
    private String x;
    private String y;

    private float size;
    private String color;
}
