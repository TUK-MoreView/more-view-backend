package com.example.moreveiw.domain.websocket.entitiy;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.page.model.dao.Page;
import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import com.example.moreveiw.domain.shape.line.model.dao.Line;
import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import com.example.moreveiw.domain.threeD.model.dao.ThreeD;
import com.example.moreveiw.domain.text.model.dao.Text;
import lombok.Getter;

@Getter
public class APIMessage {

    public enum SaveType {
        enter,
        savePage,
        saveImage,
        saveText,
        saveRectangle,
        saveCircle,
        saveLine,
        save3DData
    }

    public enum EditType {
        enter,
        editImage,
        editText,
        editRectangle,
        editCircle,
        editLine
    }

    public enum DeleteType {
        enter,
        deleteImage,
        deleteText,
        deletePage,
        deleteRectangle,
        deleteCircle,
        deleteLine,
        delete3DData
    }

    private SaveType saveType;
    private EditType editType;
    private DeleteType deleteType;
    private String roomId;
    private String userId;

    private Image image;
    private Text text;
    private Rectangle rectangle;
    private Circle circle;
    private Line line;
    private ThreeD threeD;
    private Page page;
}
