package com.example.moreveiw.domain.websocket.service;

import com.example.moreveiw.domain.image.service.ImageService;
import com.example.moreveiw.domain.shape.circle.editor.CircleEditor;
import com.example.moreveiw.domain.shape.circle.service.CircleService;
import com.example.moreveiw.domain.shape.line.editor.LineEditor;
import com.example.moreveiw.domain.shape.line.service.LineService;
import com.example.moreveiw.domain.shape.rectangle.editor.RectangleEditor;
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
        if (message.getSaveType().equals(APIMessage.SaveType.enter)) {
            // 채팅방에 session추가
            chatRoom.getSessions().add(session);
            sendMessage.sendToAllMessage(chatRoom, "새로운 사용자가 입장했습니다.");
        } else if (message.getSaveType().equals(APIMessage.SaveType.saveImage)) {
            // 이미지 저장
            sendMessage.sendToAllMessage(chatRoom, imageService.register(message.getImage()));
        } else if (message.getSaveType().equals(APIMessage.SaveType.saveText)) {
            // 텍스트 저장
            sendMessage.sendToAllMessage(chatRoom, textService.register(message.getText()));
        }


        /* -------------------------------------------- Rectangle -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveRectangle)) {
            // 사각형 저장
            sendMessage.sendToAllMessage(chatRoom, rectangleService.register(message.getRectangle()));
        } else if (message.getEditType().equals(APIMessage.EditType.editRectangle)) {
            // 사각형 수정
            rectangleService.editRectangle(message.getRectangle().getId(), RectangleEditor.builder().build());
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteRectangle)) {
            // 사각형 삭제
            rectangleService.deleteRectangle(message.getRectangle());
            sendMessage.sendToAllMessage(chatRoom, "사각형이 삭제되었습니다.");
        }


        /* -------------------------------------------- Circle -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveCircle)) {
            // 원 저장
            sendMessage.sendToAllMessage(chatRoom, circleService.register(message.getCircle()));
        } else if (message.getEditType().equals(APIMessage.EditType.editCircle)) {
            // 원 수정
            circleService.editCircle(message.getCircle().getId(), CircleEditor.builder().build());
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteCircle)) {
            // 원 삭제
            circleService.deleteCircle(message.getCircle());
            sendMessage.sendToAllMessage(chatRoom, "원이 삭제되었습니다.");
        }


        /* -------------------------------------------- Line -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveLine)) {
            // 선 저장
            sendMessage.sendToAllMessage(chatRoom, lineService.register(message.getLine()));
        } else if (message.getEditType().equals(APIMessage.EditType.editLine)) {
            // 선 수정
            lineService.editLine(message.getLine().getId(), LineEditor.builder().build());
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteLine)) {
            // 선 삭제
            lineService.deleteLine(message.getLine());
            sendMessage.sendToAllMessage(chatRoom, "선이 삭제되었습니다.");
        }
    }
}
