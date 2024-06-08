package com.example.moreveiw.domain.shape.line.model.dao;

import com.example.moreveiw.domain.shape.line.editor.LineEditor;
import com.example.moreveiw.domain.shape.rectangle.editor.RectangleEditor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "line")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private double x;
    private double y;

    private int strokeWidth;

    private String color;

    public LineEditor.LineEditorBuilder toEditor() {
        return LineEditor.builder()
                .x(x)
                .y(y)
                .strokeWidth(strokeWidth)
                .color(color);
    }

    public void edit(final LineEditor editor) {
        this.x = editor.getX();
        this.y = editor.getY();
        this.strokeWidth = editor.getStrokeWidth();
        this.color = editor.getColor();
    }

}
