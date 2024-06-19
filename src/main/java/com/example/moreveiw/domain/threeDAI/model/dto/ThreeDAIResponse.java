package com.example.moreveiw.domain.threeDAI.model.dto;

import com.example.moreveiw.domain.threeDAI.model.dao.ThreeDAI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ThreeDAIResponse {
    List<ThreeDAI> threeDAIList;
}