package com.example.moreveiw.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainP() {

        return "Main Controller";
    }
}
