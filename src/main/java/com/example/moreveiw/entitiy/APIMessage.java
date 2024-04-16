package com.example.moreveiw.entitiy;

import com.example.moreveiw.entitiy.dao.ImageDAO;
import com.example.moreveiw.entitiy.dao.TextDAO;
import lombok.Getter;

@Getter
public class APIMessage {

    public enum SaveType {
        enter, saveImage, saveText
    }

    private SaveType type;
    private String roomId;
    private String userId;

    private ImageDAO image;
    private TextDAO text;
}
