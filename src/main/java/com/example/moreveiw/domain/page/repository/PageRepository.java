package com.example.moreveiw.domain.page.repository;

import com.example.moreveiw.domain.page.model.dao.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {
}
