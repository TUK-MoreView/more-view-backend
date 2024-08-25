package com.example.moreveiw.domain.shape.line.model.dao;

import com.example.moreveiw.domain.shape.line.editor.LineEditor;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "line")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;

    private Long projectId;

    private Long pageId;

    private String crudType;
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
