package com.example.moreveiw.service;

import com.example.moreveiw.bean.image.SaveImage;
import com.example.moreveiw.bean.SendMessage;
import com.example.moreveiw.bean.text.SaveText;
import com.example.moreveiw.entitiy.APIMessage;
import com.example.moreveiw.entitiy.ProjectRoom;
import com.example.moreveiw.repositroy.ImageRepository;
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
public class ChatService {

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



    private final SaveImage saveImage;
    private final SaveText saveText;
    private final SendMessage sendMessage;
    // MessageType에 따라 로직 실행
    public void handleMessage(ProjectRoom chatRoom, APIMessage message, WebSocketSession session) {

        if(message.getType().equals(APIMessage.SaveType.enter)) {
            // 채팅방에 session추가
            chatRoom.getSessions().add(session);
            sendMessage.sendToAllMessage(chatRoom, "새로운 사용자가 입장했습니다.");
        } else if (message.getType().equals(APIMessage.SaveType.saveImage)) {
            // 이미지 저장
            sendMessage.sendToAllMessage(chatRoom, saveImage.exec(message.getImage()));
        } else if (message.getType().equals(APIMessage.SaveType.saveText)) {
            // 텍스트 저장
            sendMessage.sendToAllMessage(chatRoom, saveText.exec(message.getText()));
        }
    }
}
