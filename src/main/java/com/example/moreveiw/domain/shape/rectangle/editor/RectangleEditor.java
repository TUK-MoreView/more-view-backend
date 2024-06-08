package com.example.moreveiw.domain.shape.rectangle.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class RectangleEditor {

    private final double x;
    private final double y;
    private final float width;
    private final float height;
    private final String color;

    public static RectangleEditorBuilder builder() { return new RectangleEditorBuilder(); }

    public static class RectangleEditorBuilder {

        private double x;
        private double y;
        private float width;
        private float height;
        private String color;

        RectangleEditorBuilder() {
        }

        public RectangleEditorBuilder x(final double x) {
            this.x = x;
            return this;
        }

        public RectangleEditorBuilder y(final double y) {
            this.y = y;
            return this;
        }

        public RectangleEditorBuilder width(final float width) {
            this.width = width;
            return this;
        }

        public RectangleEditorBuilder height(final float height) {
            this.height = height;
            return this;
        }

        public RectangleEditorBuilder color(final String color) {
            if (StringUtils.hasText(color)) {
                this.color = color;
            }
            return this;
        }

        public RectangleEditor build() {
            return new RectangleEditor(x, y, width, height, color);
        }
    }

}
