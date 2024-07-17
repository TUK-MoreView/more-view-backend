package com.example.moreveiw.domain.threeD.service;

import com.example.moreveiw.domain.threeD.model.dao.ThreeD;
import com.example.moreveiw.domain.threeD.repository.ThreeDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThreeDService {
    private final ThreeDRepository threeDRepository;

    public ThreeD saveThreeD(ThreeD threeD) {
        return threeDRepository.save(threeD);
    }

    public void deleteThreeD(ThreeD threeD) {
        threeDRepository.deleteById(threeD.getThreeDId());
    }
}
