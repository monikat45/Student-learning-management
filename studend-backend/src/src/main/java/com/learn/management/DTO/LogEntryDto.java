package com.learn.management.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LogEntryDto {
    private Long id;
    private LocalDate date;
    private String category;
    private String description;
    private LocalDateTime timeSpent;

    // getters and setters...
}
