package com.example.moreveiw.domain.shape.rectangle.service;

import com.example.moreveiw.domain.shape.rectangle.editor.RectangleEditor;
import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import com.example.moreveiw.domain.shape.rectangle.repository.RectangleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RectangleService {

    private final RectangleRepository rectangleRepository;

    /* -------------------------------------------- CREATE -------------------------------------------- */
    public Rectangle register(Rectangle rectangle) {
        return rectangleRepository.save(rectangle);
    }

    /* -------------------------------------------- CREATE 끝 -------------------------------------------- */


    /* -------------------------------------------- DELETE -------------------------------------------- */
    public void deleteRectangle(Rectangle rectangle) {
        rectangleRepository.deleteById(rectangle.getId());
    }

    /* -------------------------------------------- DELETE 끝 -------------------------------------------- */


    /* -------------------------------------------- EDIT -------------------------------------------- */
    @Transactional
    public Rectangle editRectangle(final Long id, final RectangleEditor editor) {

        Rectangle rectangle = rectangleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rectangle not found"));

        RectangleEditor rectangleEditor = getRectangleEditor(editor, rectangle);
        rectangle.edit(rectangleEditor);

        return rectangleRepository.save(rectangle);
    }

    private RectangleEditor getRectangleEditor(final RectangleEditor editor, final Rectangle rectangle) {
        RectangleEditor.RectangleEditorBuilder editorBuilder = rectangle.toEditor();
        RectangleEditor rectangleEditor = editorBuilder
                .x(editor.getX())
                .y(editor.getY())
                .width(editor.getWidth())
                .height(editor.getHeight())
                .color(editor.getColor())
                .build();
        return rectangleEditor;
    }

    /* -------------------------------------------- EDIT 끝 -------------------------------------------- */

}
