package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Configuration
@ConfigurationProperties(prefix = "activity")
public class ActivityConfig {
    @NotNull
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @NotNull
    private String description;
} 