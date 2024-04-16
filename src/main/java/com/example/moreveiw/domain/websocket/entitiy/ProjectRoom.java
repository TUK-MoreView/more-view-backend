package com.example.moreveiw.domain.websocket.entitiy;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ProjectRoom {

    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ProjectRoom(String roomId) {
        this.roomId = roomId;
    }

}
