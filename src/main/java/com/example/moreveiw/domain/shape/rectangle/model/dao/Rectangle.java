package com.example.moreveiw.domain.shape.rectangle.model.dao;

import com.example.moreveiw.domain.shape.rectangle.editor.RectangleEditor;
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

    private double x;
    private double y;

    private float width;
    private float height;

    private String color;

    public RectangleEditor.RectangleEditorBuilder toEditor() {
        return RectangleEditor.builder()
                .x(x)
                .y(y)
                .width(width)
                .height(height)
                .color(color);
    }

    public void edit(final RectangleEditor editor) {
        this.x = editor.getX();
        this.y = editor.getY();
        this.width = editor.getWidth();
        this.height = editor.getHeight();
        this.color = editor.getColor();
    }

}
