package com.example.moreveiw.domain.websocket.service;

import com.example.moreveiw.domain.image.model.dao.Image;
import com.example.moreveiw.domain.image.service.ImageService;
import com.example.moreveiw.domain.shape.circle.editor.CircleEditor;
import com.example.moreveiw.domain.shape.circle.service.CircleService;
import com.example.moreveiw.domain.shape.line.editor.LineEditor;
import com.example.moreveiw.domain.shape.line.service.LineService;
import com.example.moreveiw.domain.shape.rectangle.editor.RectangleEditor;
import com.example.moreveiw.domain.shape.rectangle.service.RectangleService;
import com.example.moreveiw.domain.threeD.service.ThreeDService;
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
public class WebsocketService {

    private final ImageService imageService;
    private final TextService textService;
    private final RectangleService rectangleService;
    private final CircleService circleService;
    private final LineService lineService;
    private final SendMessage sendMessage;
    private final ThreeDService threeDService;

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

    // roomId로 채팅방 찾기 또는 생성
    public ProjectRoom findOrCreateProjectRoom(String roomId) {
        return projectRooms.computeIfAbsent(roomId, id -> {
            // ProjectRoom을 찾지 못한 경우 새 객체를 생성
            ProjectRoom newRoom = ProjectRoom.builder()
                    .roomId(id)
                    .build();
            return newRoom;
        });
    }

    // roomId로 세션 추가
    public void addSessionToRoom(String roomId, WebSocketSession session) {
        ProjectRoom projectRoom = findOrCreateProjectRoom(roomId);
        if (projectRoom != null) {
            // 기존 세션 중 닫힌 세션 제거
            projectRoom.getSessions().removeIf(s -> !s.isOpen());
            projectRoom.getSessions().add(session);
        }
    }

    // roomId로 세션 제거
    public void removeSessionFromRoom(String roomId, WebSocketSession session) {
        ProjectRoom projectRoom = findOrCreateProjectRoom(roomId);
        if (projectRoom != null) {
            projectRoom.getSessions().remove(session);
        }
    }

    // MessageType에 따라 로직 실행
    public void handleMessage(ProjectRoom chatRoom, APIMessage message, WebSocketSession session) {
        if (message.getSaveType().equals(APIMessage.SaveType.enter)
                && (message.getEditType() == null)
                && (message.getDeleteType() == null)) {
            // 채팅방에 session추가
            addSessionToRoom(chatRoom.getRoomId(), session);
            sendMessage.sendToAllMessage(chatRoom, "새로운 사용자가 입장했습니다.");
        }


        /* -------------------------------------------- image -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveImage)) {
            // 이미지 생성 후 저장
            Image image = imageService.createImage(message);
            Image savedImage = imageService.saveImage(image);
            sendMessage.sendToAllMessage(chatRoom, savedImage);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteImage)) {
            // 이미지 삭제
            Image image = imageService.createImageForDeletion(message);
            imageService.deleteImage(image);
            sendMessage.sendToAllMessage(chatRoom, "이미지가 삭제되었습니다.");
        }


        /* -------------------------------------------- Rectangle -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveRectangle)) {
            // 사각형 저장
            sendMessage.sendToAllMessage(chatRoom, rectangleService.register(message.getRectangle()));
        } else if (message.getEditType().equals(APIMessage.EditType.editRectangle)) {
            // 사각형 수정
            rectangleService.editRectangle(message.getRectangle().getRectangleId(), RectangleEditor.builder().build());
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
            circleService.editCircle(message.getCircle().getCircleId(), CircleEditor.builder().build());
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
            lineService.editLine(message.getLine().getLineId(), LineEditor.builder().build());
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteLine)) {
            // 선 삭제
            lineService.deleteLine(message.getLine());
            sendMessage.sendToAllMessage(chatRoom, "선이 삭제되었습니다.");
        }


        /* -------------------------------------------- Text -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveText)) {
            // 텍스트 저장
            sendMessage.sendToAllMessage(chatRoom, textService.saveText(message.getText()));
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteText)) {
            // 텍스트 삭제
            textService.deleteText(message.getText());
            sendMessage.sendToAllMessage(chatRoom, "텍스트가 삭제되었습니다.");
        }


        /* -------------------------------------------- ThreeD -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.save3DData)) {
            // 3D 데이터 저장
            sendMessage.sendToAllMessage(chatRoom, threeDService.saveThreeD(message.getThreeD()));
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.delete3DData)) {
            // 3D 데이터 삭제
            threeDService.deleteThreeD(message.getThreeD());
            sendMessage.sendToAllMessage(chatRoom, "3D 데이터가 삭제되었습니다.");
        }
    }
}
