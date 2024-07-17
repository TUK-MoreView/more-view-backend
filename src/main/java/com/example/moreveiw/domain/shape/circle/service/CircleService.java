package com.example.moreveiw.domain.shape.circle.service;

import com.example.moreveiw.domain.shape.circle.editor.CircleEditor;
import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import com.example.moreveiw.domain.shape.circle.repository.CircleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CircleService {

    private final CircleRepository circleRepository;

    /* -------------------------------------------- CREATE -------------------------------------------- */
    public Circle register(Circle circle) {
        return circleRepository.save(circle);
    }

    /* -------------------------------------------- CREATE 끝 -------------------------------------------- */


    /* -------------------------------------------- DELETE -------------------------------------------- */
    public void deleteCircle(Circle circle) {
        circleRepository.deleteById(circle.getCircleId());
    }

    /* -------------------------------------------- DELETE 끝 -------------------------------------------- */


    /* -------------------------------------------- EDIT -------------------------------------------- */
    @Transactional
    public Circle editCircle(final Long id, final CircleEditor editor) {

        Circle circle = circleRepository.findByCircleId(id)
                .orElseThrow(() -> new IllegalArgumentException("Circle not found"));

        CircleEditor circleEditor = getCircleEditor(editor, circle);
        circle.edit(circleEditor);

        return circleRepository.save(circle);
    }

    private CircleEditor getCircleEditor(final CircleEditor editor, final Circle circle) {
        CircleEditor.CircleEditorBuilder editorBuilder = circle.toEditor();
        CircleEditor circleEditor = editorBuilder
                .x(editor.getX())
                .y(editor.getY())
                .radiusX(editor.getRadiusX())
                .radiusY(editor.getRadiusY())
                .color(editor.getColor())
                .id(editor.getId())
                .type(editor.getType())
                .build();
        return circleEditor;
    }

    /* -------------------------------------------- EDIT 끝 -------------------------------------------- */


}
