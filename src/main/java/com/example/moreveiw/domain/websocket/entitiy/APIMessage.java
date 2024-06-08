package com.example.moreveiw.domain.websocket.entitiy;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import com.example.moreveiw.domain.shape.line.model.dao.Line;
import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import com.example.moreveiw.domain.text.model.dao.Text;
import lombok.Getter;

@Getter
public class APIMessage {

    public enum SaveType {
        enter, saveImage, saveText, saveRectangle, saveCircle, saveLine
    }

    public enum EditType {
        enter, editImage, editText, editRectangle, editCircle
    }

    public enum DeleteType {
        enter, deleteImage, deleteText, deleteRectangle, deleteCircle
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
}
