package com.learn.management.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseDto {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    // getters and setters

}
