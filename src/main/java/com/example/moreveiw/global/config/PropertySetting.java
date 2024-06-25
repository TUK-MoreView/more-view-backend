package com.example.moreveiw.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:secrets.properties", ignoreResourceNotFound = true),
})
public class PropertySetting {
}