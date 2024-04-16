//package com.moreview.domain.member.controller;
//
//import com.moreview.domain.member.dto.request.AddMemberRequest;
//import com.moreview.domain.member.service.MemberService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("api/v1")
//public class MemberApiController {
//
//    private final MemberService memberService;
//
//    @PostMapping("/members")
//    public String singup(AddMemberRequest request) {
//        memberService.save(request);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/signup")
//    public String signup() {
//        return "signup";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "oauthLogin";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/login";
//    }
//}
