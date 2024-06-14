package com.example.moreveiw.domain.threeDAI.controller;

import com.example.moreveiw.domain.threeDAI.model.dto.ThreeDAIResponse;
import com.example.moreveiw.domain.threeDAI.service.ThreeDAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
public class ThreeDAIController {
    private final ThreeDAIService threeDAIService;

    @PostMapping("/treeDAI")
    public String makeThreeDAI(@RequestBody String name) throws IOException {
        return threeDAIService.makeThreeDAI(name);
    }

    @GetMapping("/treeDAI")
    public ThreeDAIResponse getThreeDAI(@RequestParam String name) {
        return threeDAIService.getThreeDAI(name);
    }

}
