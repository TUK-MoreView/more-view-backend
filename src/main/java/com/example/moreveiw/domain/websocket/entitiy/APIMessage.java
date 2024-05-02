package com.example.moreveiw.domain.websocket.entitiy;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.text.model.dao.Text;
import com.example.moreveiw.domain.threeD.model.dao.ThreeD;
import lombok.Getter;

@Getter
public class APIMessage {

    public enum SaveType {
        enter, saveImage, saveText, save3DData
    }

    public enum DeleteType {
        enter, deleteImage, deleteText, delete3DData
    }

    private SaveType saveType;
    private DeleteType deleteType;
    private String roomId;
    private String userId;

    private Image image;
    private Text text;
    private ThreeD threeD;
}
