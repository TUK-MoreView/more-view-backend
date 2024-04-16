package com.example.moreveiw.domain.base;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


@EnableJpaAuditing
@Configuration
public class BaseEntityConfig {
    // 등록자, 수정자를 처리해주는 AuditorAware 스프링 빈 등록
    // 세션 정보나, 스프링 시큐리티 로그인 정보에서 ID를 받음

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return Optional.of("Anonymous");
            }

            String name = authentication.getName();
            return Optional.of(name);
        };
    }
}
