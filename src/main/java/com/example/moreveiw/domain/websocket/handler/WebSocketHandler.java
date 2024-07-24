package com.example.moreveiw.domain.websocket.handler;

import com.example.moreveiw.domain.websocket.entitiy.APIMessage;
import com.example.moreveiw.domain.websocket.entitiy.ProjectRoom;
import com.example.moreveiw.domain.websocket.service.WebsocketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final WebsocketService chatService;
    private final ObjectMapper objectMapper;
    private String roomId;
    private ProjectRoom chatRoom;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();

        // 메시지를 APIMessage 객체로 변환
        APIMessage chatMessage = objectMapper.readValue(payload, APIMessage.class);

        // UUID 가져오기
        roomId = chatMessage.getRoomId();

        // CharRoom 찾기 또는 생성
        ProjectRoom chatRoom = chatService.findOrCreateProjectRoom(roomId);

        // 로직 실행
        chatService.handleMessage(chatRoom, chatMessage,session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        if (roomId != null) {
            chatService.addSessionToRoom(roomId, session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        if (roomId != null) {
            chatService.removeSessionFromRoom(roomId, session);
        }
    }
}
