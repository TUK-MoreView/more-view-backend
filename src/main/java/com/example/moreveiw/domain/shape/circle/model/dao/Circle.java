package com.example.moreveiw.domain.shape.circle.model.dao;

import com.example.moreveiw.domain.shape.circle.editor.CircleEditor;
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

    private double x;
    private double y;

    private Long radiusX;
    private Long radiusY;

    private String color;

    public CircleEditor.CircleEditorBuilder toEditor() {
        return CircleEditor.builder()
                .x(x)
                .y(y)
                .radiusX(radiusX)
                .radiusY(radiusY)
                .color(color);
    }

    public void edit(final CircleEditor editor) {
        this.x = editor.getX();
        this.y = editor.getY();
        this.radiusX = editor.getRadiusX();
        this.radiusY = editor.getRadiusY();
        this.color = editor.getColor();
    }

}
