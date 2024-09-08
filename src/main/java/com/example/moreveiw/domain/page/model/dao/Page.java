package com.example.moreveiw.domain.page.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "page")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageDBId;

    private String crudType;
    private Long projectId;
    private Long pageId;
    private String pageType;

}
