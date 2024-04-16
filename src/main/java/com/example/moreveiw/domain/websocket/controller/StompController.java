package com.example.moreveiw.domain.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompController {

    @MessageMapping("/hello")
    @SendTo("/sub/greetings")
    public String greeting(String message) throws Exception {
        return message;
    }
}
