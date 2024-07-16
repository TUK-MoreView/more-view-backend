package com.example.moreveiw.domain.shape.line.service;

import com.example.moreveiw.domain.shape.line.model.dao.Line;
import com.example.moreveiw.domain.shape.line.repository.LineRepository;
import com.example.moreveiw.domain.shape.line.editor.LineEditor;
import com.example.moreveiw.domain.shape.line.model.dao.Line;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    /* -------------------------------------------- CREATE -------------------------------------------- */
    public Line register(Line line) {
        return lineRepository.save(line);
    }

    /* -------------------------------------------- CREATE 끝 -------------------------------------------- */


    /* -------------------------------------------- DELETE -------------------------------------------- */
    public void deleteLine(Line line) {
        lineRepository.deleteById(line.getLine_id());
    }

    /* -------------------------------------------- DELETE 끝 -------------------------------------------- */


    /* -------------------------------------------- EDIT -------------------------------------------- */
    @Transactional
    public Line editLine(final Long id, final LineEditor editor) {

        Line line = lineRepository.findByLineId(id)
                .orElseThrow(() -> new IllegalArgumentException("Line not found"));

        LineEditor lineEditor = getLineEditor(editor, line);
        line.edit(lineEditor);

        return lineRepository.save(line);
    }

    private LineEditor getLineEditor(final LineEditor editor, final Line line) {
        LineEditor.LineEditorBuilder editorBuilder = line.toEditor();
        LineEditor lineEditor = editorBuilder
                .id(editor.getId())
                .points(editor.getPoints())
                .type(editor.getType())
                .stroke(editor.getStroke())
                .strokeWidth(editor.getStrokeWidth())
                .build();
        return lineEditor;
    }

    /* -------------------------------------------- EDIT 끝 -------------------------------------------- */

}
