package com.example.moreveiw.domain.websocket.controller;

import com.example.moreveiw.domain.websocket.entitiy.ProjectRoom;
import com.example.moreveiw.domain.websocket.service.WebsocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat-rooms")
@Tag(name = "WebSocket Controller", description = "WebSocket 관련 API")
public class ChatController {

    private final WebsocketService chatService;

    // RoomName으로 채팅방 생성
    @PostMapping
    @Operation(summary = "채팅방 생성", description = "새로운 채팅방을 생성합니다.")
    public ProjectRoom createRoom() {
        return chatService.createProjectRoom();
    }
}
