package com.example.moreveiw.domain.member.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MemberListResponse {

    private List<MemberResponse> memberList;
}
