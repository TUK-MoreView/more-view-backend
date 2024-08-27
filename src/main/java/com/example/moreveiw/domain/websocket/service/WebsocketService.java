package com.example.moreveiw.domain.websocket.service;
import com.example.moreveiw.domain.shape.circle.model.dao.Circle;
import com.example.moreveiw.domain.shape.line.model.dao.Line;
import com.example.moreveiw.domain.shape.rectangle.model.dao.Rectangle;
import com.example.moreveiw.domain.text.model.dao.Text;
import com.example.moreveiw.domain.threeD.model.dao.ThreeD;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
            if(message.getImage().getImageId()==null){
                savedImage.setCrudType("create");
            }
            else{
                savedImage.setCrudType("update");
            }
            sendMessage.sendToAllMessage(chatRoom, savedImage);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteImage)) {
            // 이미지 삭제
            Image image = imageService.createImageForDeletion(message);
            imageService.deleteImage(image);
            Image deletedImage = new Image();
            deletedImage.setId(image.getId());
            deletedImage.setCrudType("delete");
            sendMessage.sendToAllMessage(chatRoom, deletedImage);
        }


        /* -------------------------------------------- Rectangle -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveRectangle)) {
            // 사각형 저장
            Rectangle rectangle = rectangleService.register(message.getRectangle());
            rectangle.setCrudType("create");
            sendMessage.sendToAllMessage(chatRoom, rectangle);
        } else if (message.getEditType().equals(APIMessage.EditType.editRectangle)) {
            // 사각형 수정
            Rectangle rectangle = rectangleService.editRectangle(message.getRectangle().getRectangleId(), RectangleEditor.builder().build());
            rectangle.setCrudType("update");
            sendMessage.sendToAllMessage(chatRoom, rectangle);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteRectangle)) {
            // 사각형 삭제
            rectangleService.deleteRectangle(message.getRectangle());
            Rectangle deletedRectangle = new Rectangle();
            deletedRectangle.setId(message.getRectangle().getId());
            deletedRectangle.setCrudType("delete");
            sendMessage.sendToAllMessage(chatRoom, deletedRectangle);
        }


        /* -------------------------------------------- Circle -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveCircle)) {
            // 원 저장
            Circle circle = circleService.register(message.getCircle());
            circle.setCrudType("create");
            sendMessage.sendToAllMessage(chatRoom, circle);
        } else if (message.getEditType().equals(APIMessage.EditType.editCircle)) {
            // 원 수정
            Circle circle = circleService.editCircle(message.getCircle().getCircleId(), CircleEditor.builder().build());
            circle.setCrudType("update");
            sendMessage.sendToAllMessage(chatRoom, circle);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteCircle)) {
            // 원 삭제
            circleService.deleteCircle(message.getCircle());
            Circle deletedCircle = new Circle();
            deletedCircle.setId(message.getCircle().getId());
            deletedCircle.setCrudType("delete");
            sendMessage.sendToAllMessage(chatRoom, deletedCircle);
        }


        /* -------------------------------------------- Line -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveLine)) {
            // 선 저장
            Line line = lineService.register(message.getLine());
            line.setCrudType("create");
            sendMessage.sendToAllMessage(chatRoom, line);
        } else if (message.getEditType().equals(APIMessage.EditType.editLine)) {
            // 선 수정
            Line line = lineService.editLine(message.getLine().getLineId(), LineEditor.builder().build());
            line.setCrudType("update");
            sendMessage.sendToAllMessage(chatRoom, line);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteLine)) {
            // 선 삭제
            lineService.deleteLine(message.getLine());
            Line deletedLine = new Line();
            deletedLine.setId(message.getLine().getId());
            deletedLine.setCrudType("delete");
            sendMessage.sendToAllMessage(chatRoom, deletedLine);
        }


        /* -------------------------------------------- Text -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.saveText)) {
            // 텍스트 저장
            Text text = textService.saveText(message.getText());
            if(message.getText().getTextId()==null){
                text.setCrudType("create");
            }
            else{
                text.setCrudType("update");
            }
            sendMessage.sendToAllMessage(chatRoom, text);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.deleteText)) {
            // 텍스트 삭제
            textService.deleteText(message.getText());
            Text deletedText = new Text();
            deletedText.setId(message.getText().getId());
            deletedText.setCrudType("delete");
            sendMessage.sendToAllMessage(chatRoom, deletedText);
        }


        /* -------------------------------------------- ThreeD -------------------------------------------- */
        else if (message.getSaveType().equals(APIMessage.SaveType.save3DData)) {
            // 3D 데이터 저장
            ThreeD threeD = threeDService.saveThreeD(message.getThreeD());
            if(message.getThreeD().getThreeDId()==null){
                threeD.setCrudType("create");
            }
            else{
                threeD.setCrudType("update");
            }
            sendMessage.sendToAllMessage(chatRoom, threeD);
        } else if (message.getDeleteType().equals(APIMessage.DeleteType.delete3DData)) {
            // 3D 데이터 삭제
            threeDService.deleteThreeD(message.getThreeD());
            ThreeD deletedThreeD = new ThreeD();
            deletedThreeD.setId(message.getThreeD().getId());
            deletedThreeD.setCrudType("delete");
            sendMessage.sendToAllMessage(chatRoom, deletedThreeD);
        }
    }
}
