package com.example.moreveiw.domain.member.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authority")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
