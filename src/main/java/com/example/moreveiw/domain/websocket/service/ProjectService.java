package com.example.moreveiw.domain.websocket.service;

import com.example.moreveiw.domain.image.service.ImageService;
import com.example.moreveiw.domain.shape.circle.service.CircleService;
import com.example.moreveiw.domain.shape.line.model.service.LineService;
import com.example.moreveiw.domain.shape.rectangle.service.RectangleService;
import com.example.moreveiw.domain.websocket.bean.SendMessage;
import com.example.moreveiw.domain.text.service.TextService;
import com.example.moreveiw.domain.websocket.entitiy.APIMessage;
import com.example.moreveiw.domain.websocket.entitiy.ProjectRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    // repository 대신 사용
    private Map<String, ProjectRoom> projectRooms;

    // 서버가 실행되면 실행
    @PostConstruct
    public void init() {
        projectRooms = new LinkedHashMap<>();
    }

    // 채팅방 생성
    public ProjectRoom createProjectRoom() {
        String randomId = UUID.randomUUID().toString();

        ProjectRoom projectRoom = ProjectRoom.builder()
                .roomId(randomId)
                .build();

        // chatRooms(목록)에 채팅방 추가
        projectRooms.put(projectRoom.getRoomId(), projectRoom);
        return projectRoom;
    }

    // roomId로 채팅방 찾기
    public ProjectRoom findProjectRoom(String roomId) {
        return projectRooms.get(roomId);
    }


    private final ImageService imageService;
    private final TextService textService;
    private final RectangleService rectangleService;
    private final CircleService circleService;
    private final LineService lineService;
    private final SendMessage sendMessage;

    // MessageType에 따라 로직 실행
    public void handleMessage(ProjectRoom chatRoom, APIMessage message, WebSocketSession session) {
        if (message.getType().equals(APIMessage.SaveType.enter)) {
            // 채팅방에 session추가
            chatRoom.getSessions().add(session);
            sendMessage.sendToAllMessage(chatRoom, "새로운 사용자가 입장했습니다.");
        } else if (message.getType().equals(APIMessage.SaveType.saveImage)) {
            // 이미지 저장
            sendMessage.sendToAllMessage(chatRoom, imageService.exec(message.getImage()));
        } else if (message.getType().equals(APIMessage.SaveType.saveText)) {
            // 텍스트 저장
            sendMessage.sendToAllMessage(chatRoom, textService.exec(message.getText()));
        } else if (message.getType().equals(APIMessage.SaveType.saveRectangle)) {
            // 사각형 저장
            sendMessage.sendToAllMessage(chatRoom, rectangleService.exec(message.getRectangle()));
        } else if (message.getType().equals(APIMessage.SaveType.saveCircle)) {
            // 원 저장
            sendMessage.sendToAllMessage(chatRoom, circleService.exec(message.getCircle()));
        } else if (message.getType().equals(APIMessage.SaveType.saveLine)) {
            // 선 저장
            sendMessage.sendToAllMessage(chatRoom, lineService.exec(message.getLine()));
        }

    }
}
