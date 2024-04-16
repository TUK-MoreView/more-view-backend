package com.example.moreveiw.bean;

import com.example.moreveiw.domain.ProjectRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SendMessageBean {
    private final ObjectMapper objectMapper;

    // ChatRoom에 있는 모든 session에 메시지 전송
    public <T> void sendToAllMessage(ProjectRoom chatRoom, T message) {
        chatRoom.getSessions().forEach(session -> {
            sendMessage(session, message);
        });
    }

    // session에 메시지 전송
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            throw new IllegalArgumentException("메시지 전송 실패");
        }
    }
}
