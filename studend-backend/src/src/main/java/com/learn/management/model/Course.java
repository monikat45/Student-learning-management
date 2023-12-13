package com.learn.management.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @FutureOrPresent(message = "Start date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    @Future(message = "End date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDate setStartDate(LocalDate startDate) {
        return this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate setEndDate(LocalDate endDate) {
        return this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


}
