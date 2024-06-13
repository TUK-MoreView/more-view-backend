//package com.example.moreveiw.global.security.oauth2.dto.response;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//@AllArgsConstructor
//public class KakaoResponse implements OAuth2Response {
//
//    private final Map<String, Object> attriute;
//
//    @Override
//    public String getProvider() {
//
//        return "kakao";
//    }
//
//    @Override
//    public String getProviderId() {
//
//        return attriute.get("id").toString();
//    }
//
//    @Override
//    public String getEmail() {
//        Map<String, Object> kakaoAccount = (Map<String, Object>) attriute.get("kakao_account");
//        return (String) kakaoAccount.get("email");
//    }
//
//    @Override
//    public String getName() {
//        Map<String, Object> kakaoAccount = (Map<String, Object>) attriute.get("kakao_account");
//        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
//        return (String) profile.get("nickname");
//    }
//
//
//}
