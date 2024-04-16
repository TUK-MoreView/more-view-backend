package com.example.moreveiw.domain.websocket.controller;

import com.example.moreveiw.domain.websocket.entitiy.ProjectRoom;
import com.example.moreveiw.domain.websocket.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat-rooms")
public class ChatController {

    private final ProjectService chatService;

    // RoomName으로 채팅방 생성
    @PostMapping
    public ProjectRoom createRoom() {
        return chatService.createProjectRoom();
    }
}
