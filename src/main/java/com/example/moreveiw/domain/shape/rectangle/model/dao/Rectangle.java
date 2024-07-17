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
    private Long rectangleId;

    private Long projectId;

    private String id;
    private int x;
    private int y;

    private int width;
    private int height;

    private String fill;
    private String type;

    public RectangleEditor.RectangleEditorBuilder toEditor() {
        return RectangleEditor.builder()
                .x(x)
                .y(y)
                .width(width)
                .height(height)
                .color(fill)
                .id(id)
                .type(type);
    }

    public void edit(final RectangleEditor editor) {
        this.x = editor.getX();
        this.y = editor.getY();
        this.width = editor.getWidth();
        this.height = editor.getHeight();
        this.fill = editor.getColor();
        this.id = editor.getId();
        this.type = editor.getType();
    }

}
