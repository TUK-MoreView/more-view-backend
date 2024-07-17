package com.example.moreveiw.domain.shape.line.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class LineEditor {

    private final int strokeWidth;
    private final String stroke;
    private final String type;
    private final String id;
    private final int[] points;

    public static LineEditorBuilder builder() {
        return new LineEditorBuilder();
    }

    public static class LineEditorBuilder {

        private int strokeWidth;
        private String stroke;
        private String type;
        private String id;
        private int[] points = new int[4];

        LineEditorBuilder() {
        }

        public LineEditorBuilder strokeWidth(final int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public LineEditorBuilder stroke(final String stroke) {
            if (StringUtils.hasText(stroke)) {
                this.stroke = stroke;
            }
            return this;
        }

        public LineEditorBuilder type(final String type) {
            if (StringUtils.hasText(type)) {
                this.type = type;
            }
            return this;
        }

        public LineEditorBuilder id(final String id) {
            if (StringUtils.hasText(id)) {
                this.id = id;
            }
            return this;
        }

        public LineEditorBuilder points(final int[] points) {
            if (points != null) {
                this.points = points;
            }
            return this;
        }


        public LineEditor build() {
            return new LineEditor(strokeWidth, stroke, type, id, points);
        }
    }
}
