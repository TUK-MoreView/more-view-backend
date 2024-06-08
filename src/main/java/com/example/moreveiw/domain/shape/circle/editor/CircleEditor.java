package com.example.moreveiw.domain.shape.circle.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class CircleEditor {

    private final double x;
    private final double y;
    private final long radiusX;
    private final long radiusY;
    private final String color;

    public static CircleEditor.CircleEditorBuilder builder() { return new CircleEditor.CircleEditorBuilder(); }

    public static class CircleEditorBuilder {

        private double x;
        private double y;
        private long radiusX;
        private long radiusY;
        private String color;

        CircleEditorBuilder() {
        }

        public CircleEditor.CircleEditorBuilder x(final double x) {
            this.x = x;
            return this;
        }

        public CircleEditor.CircleEditorBuilder y(final double y) {
            this.y = y;
            return this;
        }

        public CircleEditor.CircleEditorBuilder radiusX(final long radiusX) {
            this.radiusX = radiusX;
            return this;
        }

        public CircleEditor.CircleEditorBuilder radiusY(final long radiusY) {
            this.radiusY = radiusY;
            return this;
        }

        public CircleEditor.CircleEditorBuilder color(final String color) {
            if (StringUtils.hasText(color)) {
                this.color = color;
            }
            return this;
        }

        public CircleEditor build() {
            return new CircleEditor(x, y, radiusX, radiusY, color);
        }
    }
}
