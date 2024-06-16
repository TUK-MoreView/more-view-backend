package com.example.moreveiw.domain.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequiredArgsConstructor
@Tag(name = "Stomp Controller", description = "STOMP WebSocket API")
public class StompController {

    @MessageMapping("/hello")
    @SendTo("/sub/greetings")
    @Operation(summary = "메시지 보내기", description = "메시지를 받아서 구독자에게 보냅니다.")
    public String greeting(String message) throws Exception {
        return message;
    }
}
