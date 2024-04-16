package com.example.moreveiw.global.security.oauth2.service;

import com.example.moreveiw.domain.member.editor.MemberEditor;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.member.repository.MemberRepository;
import com.example.moreveiw.global.security.oauth2.dto.CustomOAuth2User;
import com.example.moreveiw.global.security.oauth2.dto.response.KakaoResponse;
import com.example.moreveiw.global.security.oauth2.dto.MemberDto;
import com.example.moreveiw.global.security.oauth2.dto.response.NaverResponse;
import com.example.moreveiw.global.security.oauth2.dto.response.OAuth2Response;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        // 리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듦
        String memberName = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        Member existData = memberRepository.findByMemberName(memberName);

        if (existData == null) {

            Member member = Member.builder()
                    .memberName(memberName)
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .role("ROLE_MEMBER")
                    .build();

            memberRepository.save(member);

            MemberDto memberDto = MemberDto.builder()
                    .memberName(memberName)
                    .name(oAuth2Response.getName())
                    .role("ROLE_MEMBER")
                    .build();

            return new CustomOAuth2User(memberDto);
        }

        MemberEditor memberEditor = MemberEditor.builder()
                .name(oAuth2Response.getName())
                .email(oAuth2Response.getEmail())
                .build();

        existData.toEditor()
                .name(memberEditor.getName())
                .email(memberEditor.getEmail())
                .build();


        memberRepository.save(existData);

        MemberDto memberDto = MemberDto.builder()
                .memberName(memberName)
                .name(oAuth2Response.getName())
                .role(existData.getRole())
                .build();


        return new CustomOAuth2User(memberDto);

    }
}
