package com.example.moreveiw.domain.threeDAI.controller;

import com.example.moreveiw.domain.threeDAI.model.dto.ThreeDAIResponse;
import com.example.moreveiw.domain.threeDAI.service.ThreeDAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
@Tag(name = "3D AI Controller", description = "3D AI 관련 API")
public class ThreeDAIController {
    private final ThreeDAIService threeDAIService;

    @PostMapping("/treeDAI")
    @Operation(summary = "3D AI 생성", description = "이름을 받아 3D AI를 생성합니다.")
    public String makeThreeDAI(@RequestBody String name) throws IOException {
        return threeDAIService.makeThreeDAI(name);
    }

    @GetMapping("/treeDAI")
    @Operation(summary = "3D AI 조회", description = "이름을 받아 3D AI 정보를 조회합니다.")
    public ThreeDAIResponse getThreeDAI(@RequestParam String name) {
        return threeDAIService.getThreeDAI(name);
    }

}
