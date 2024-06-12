package com.example.moreveiw.domain.threeDAI.service;

import com.example.moreveiw.domain.threeDAI.model.dao.ThreeDAI;
import com.example.moreveiw.domain.threeDAI.model.dto.ThreeDAIResponse;
import com.example.moreveiw.domain.threeDAI.repository.ThreeDAIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThreeDAIService {
    private final ThreeDAIRepository threeDAIRepository;
    public String saveThreeDAI(String name, String dataUrl) {
        threeDAIRepository.save(ThreeDAI.builder().
                name(name).
                dataUri(dataUrl).build());
        return "success";
    }

    public ThreeDAIResponse getThreeDAI(String name) {
        ThreeDAI threeDAI = threeDAIRepository.findByName(name).get();
        ThreeDAIResponse threeDAIResponse = ThreeDAIResponse.builder().
                id(threeDAI.getId()).
                dataUri(threeDAI.getDataUri()).build();
        return threeDAIResponse;
    }
}
