package com.example.moreveiw.domain.threeDAI.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "3DAI")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreeDAI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataUri;
}
