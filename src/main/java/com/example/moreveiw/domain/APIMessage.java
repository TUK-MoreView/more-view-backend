package com.example.moreveiw.domain;

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
