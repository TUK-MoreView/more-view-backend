package com.example.moreveiw.domain.shape.rectangle.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class RectangleEditor {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String color;
    private final String id;
    private final String type;

    public static RectangleEditorBuilder builder() { return new RectangleEditorBuilder(); }

    public static class RectangleEditorBuilder {

        private int x;
        private int y;
        private int width;
        private int height;
        private String color;
        private String id;
        private String type;

        RectangleEditorBuilder() {
        }

        public RectangleEditorBuilder x(final int x) {
            this.x = x;
            return this;
        }

        public RectangleEditorBuilder y(final int y) {
            this.y = y;
            return this;
        }

        public RectangleEditorBuilder width(final int width) {
            this.width = width;
            return this;
        }

        public RectangleEditorBuilder height(final int height) {
            this.height = height;
            return this;
        }

        public RectangleEditorBuilder color(final String color) {
            if (StringUtils.hasText(color)) {
                this.color = color;
            }
            return this;
        }

        public RectangleEditorBuilder id(final String id) {
            if (StringUtils.hasText(id)) {
                this.id = id;
            }
            return this;
        }

        public RectangleEditorBuilder type(final String type) {
            if (StringUtils.hasText(type)) {
                this.type = type;
            }
            return this;
        }

        public RectangleEditor build() {
            return new RectangleEditor(x, y, width, height, color, id, type);
        }
    }
}
