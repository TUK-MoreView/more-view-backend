package com.example.moreveiw.domain.websocket.handler;

import com.example.moreveiw.domain.websocket.entitiy.APIMessage;
import com.example.moreveiw.domain.websocket.entitiy.ProjectRoom;
import com.example.moreveiw.domain.websocket.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final ProjectService chatService;
    private final ObjectMapper objectMapper;
    private String roomId;
    private ProjectRoom chatRoom;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();

        // 메시지를 APIMessage 객체로 변환
        APIMessage chatMessage = objectMapper.readValue(payload, APIMessage.class);

        // UUID 가져오기
        roomId = chatMessage.getRoomId();

        // CharRoom 찾기
        chatRoom = chatService.findProjectRoom(roomId);

        // 로직 실행
        chatService.handleMessage(chatRoom, chatMessage,session);
    }
}
