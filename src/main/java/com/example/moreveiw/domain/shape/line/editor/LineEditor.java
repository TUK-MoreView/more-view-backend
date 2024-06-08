package com.example.moreveiw.domain.shape.line.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class LineEditor {
    
    private final double x;
    private final double y;
    private final int strokeWidth;
    private final String color;

    public static LineEditorBuilder builder() { return new LineEditorBuilder(); }

    public static class LineEditorBuilder {

        private double x;
        private double y;
        private int strokeWidth;
        private String color;

        LineEditorBuilder() {
        }

        public LineEditorBuilder x(final double x) {
            this.x = x;
            return this;
        }

        public LineEditorBuilder y(final double y) {
            this.y = y;
            return this;
        }

        public LineEditorBuilder strokeWidth(final int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public LineEditor.LineEditorBuilder color(final String color) {
            if (StringUtils.hasText(color)) {
                this.color = color;
            }
            return this;
        }

        public LineEditor build() {
            return new LineEditor(x, y, strokeWidth, color);
        }
    }
}
