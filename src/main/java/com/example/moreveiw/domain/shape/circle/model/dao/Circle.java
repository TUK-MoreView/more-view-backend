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
    private Long circle_id;

    private Long projectId;

    private String id;
    private int x;
    private int y;

    private Long radiusX;
    private Long radiusY;

    private String fill;

    private String type;

    public CircleEditor.CircleEditorBuilder toEditor() {
        return CircleEditor.builder()
                .x(x)
                .y(y)
                .radiusX(radiusX)
                .radiusY(radiusY)
                .color(fill)
                .id(id)
                .type(type);
    }

    public void edit(final CircleEditor editor) {
        this.x = editor.getX();
        this.y = editor.getY();
        this.id = editor.getId();
        this.type = editor.getType();
        this.radiusX = editor.getRadiusX();
        this.radiusY = editor.getRadiusY();
        this.fill = editor.getColor();
    }

}
