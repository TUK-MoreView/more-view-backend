package com.example.moreveiw.domain.text.model.dao;
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
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long textId;

    private Long projectId;
    private Long pageId;

    private String id;

    private String text;
    private int x;
    private int y;

    private int size;
    private String color;
}
