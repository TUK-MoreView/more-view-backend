package com.example.moreveiw.domain.websocket.entitiy;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import com.example.moreveiw.domain.text.model.dao.Text;
import lombok.Getter;

@Getter
public class  APIMessage {

    public enum SaveType {
        enter, saveImage, saveText, saveRectangle, saveCircle
    }

    private SaveType type;
    private String roomId;
    private String userId;

    private Image image;
    private Text text;
    private Rectangle rectangle;
    private Circle circle;
}
