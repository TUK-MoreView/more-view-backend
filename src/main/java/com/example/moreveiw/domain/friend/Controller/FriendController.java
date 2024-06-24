package com.example.moreveiw.domain.friend.Controller;

import com.example.moreveiw.domain.friend.Model.DTO.Friend.Request.FriendRequestDTO;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendListResponseDTO;
import com.example.moreveiw.domain.friend.Model.DTO.Friend.Response.FriendRequestListResponseDTO;
import com.example.moreveiw.domain.friend.Model.DTO.StateResponseDTO;
import com.example.moreveiw.domain.friend.Model.ENUM.FriendSort;
import com.example.moreveiw.domain.friend.Service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class FriendController {

    private final FriendService friendService;

    //친구 추가 요청
    @Operation(summary = "친구 추가 요청")
    @PostMapping("/friend/request")
    public StateResponseDTO requestFriend(@RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.requestFriend(friendRequestDTO);
    }

    //친구 추가 요청받은 목록
    @Operation(summary = "친구 추가 요청보낸 목록", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/friend/requests/{memberId}")
    public List<FriendRequestListResponseDTO> requestList(@PathVariable(value = "memberId") Long memberId,
                                                          @RequestParam(value = "page") @Min(0) Integer page,
                                                          @RequestParam(value = "size") @Min(0) @Max(10) Integer size) {
        return friendService.requestList(memberId, page, size);
    }

    //친구 추가 보낸 요청 삭제
    @Operation(summary = "친구 추가 보낸 요청 삭제")
    @DeleteMapping("/friend/request")
    public StateResponseDTO deleteRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.deleteRequest(friendRequestDTO);
    }

    //친구 추가 요청보낸 목록
    @Operation(summary = "친구 추가 요청받은 목록", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/friend/requested/{memberId}")
    public List<FriendRequestListResponseDTO> requestedList(@PathVariable(value = "memberId") Long memberId,
                                                            @RequestParam(value = "page") @Min(0) Integer page,
                                                            @RequestParam(value = "size") @Min(0) @Max(10) Integer size) {
        return friendService.requestedList(memberId, page, size);
    }

    //친구 추가 수락
    @Operation(summary = "친구 추가 수락")
    @PostMapping("/friend/accept")
    public StateResponseDTO acceptFriend(@RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.acceptFriend(friendRequestDTO);
    }

    //친구 추가 거절
    @Operation(summary = "친구 추가 거절")
    @PostMapping("/friend/reject")
    public StateResponseDTO rejectFriend(@RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.rejectFriend(friendRequestDTO);
    }

    //친구 목록
    @Operation(summary = "친구 목록", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/friends/{memberId}")
    public List<FriendListResponseDTO> friendList(@PathVariable Long memberId,
                                                  @RequestParam(value = "page") @Min(0) Integer page,
                                                  @RequestParam(value = "size") @Min(0) @Max(10) Integer size,
                                                  @RequestParam(value = "sort", defaultValue = "TIME") FriendSort sort) {
        return friendService.friendList(memberId, page, size, sort);
    }

    //친구 삭제
    @Operation(summary = "친구 삭제")
    @DeleteMapping("/friend")
    public StateResponseDTO deleteFriend(@RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.deleteFriend(friendRequestDTO);
    }
}
