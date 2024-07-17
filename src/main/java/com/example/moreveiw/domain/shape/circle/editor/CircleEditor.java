package com.example.moreveiw.domain.shape.circle.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class CircleEditor {

    private final int x;
    private final int y;
    private final long radiusX;
    private final long radiusY;
    private final String color;
    private final String id;
    private final String type;

    public static CircleEditor.CircleEditorBuilder builder() { return new CircleEditor.CircleEditorBuilder(); }

    public static class CircleEditorBuilder {

        private int x;
        private int y;
        private long radiusX;
        private long radiusY;
        private String color;
        private String id;
        private String type;


        CircleEditorBuilder() {
        }

        public CircleEditor.CircleEditorBuilder x(final int x) {
            this.x = x;
            return this;
        }

        public CircleEditor.CircleEditorBuilder y(final int y) {
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

        public CircleEditor.CircleEditorBuilder id(final String id) {
            if (StringUtils.hasText(id)) {
                this.id = id;
            }
            return this;
        }

        public CircleEditor.CircleEditorBuilder type(final String type) {
            if (StringUtils.hasText(type)) {
                this.type = type;
            }
            return this;
        }

        public CircleEditor build() {
            return new CircleEditor(x, y,  radiusX, radiusY, color, id, type);
        }
    }
}
