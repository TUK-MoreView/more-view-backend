package com.example.moreveiw.domain.shape.line.model.dao;

import com.example.moreveiw.domain.shape.line.editor.LineEditor;
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
    private Long line_id;

    private Long projectId;

    private String id;

    private int[] points = new int[4];
    private int strokeWidth;

    private String stroke;

    private String type;

    public LineEditor.LineEditorBuilder toEditor() {
        return LineEditor.builder()
                .strokeWidth(strokeWidth)
                .stroke(stroke)
                .id(id)
                .type(type)
                .points(points);
    }

    public void edit(final LineEditor editor) {
        this.id = editor.getId();
        this.type = editor.getType();
        this.points = editor.getPoints();
        this.strokeWidth = editor.getStrokeWidth();
        this.stroke = editor.getStroke();
    }

}
