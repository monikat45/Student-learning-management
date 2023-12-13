package com.learn.management.service;

import com.learn.management.DTO.CourseDto;
import com.learn.management.model.Course;
import com.learn.management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {


    private static CourseRepository courseRepository;

    @Autowired(required = true)
    public AdminService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public static void createCourse(CourseDto courseDto) {
        // Validate business rules
        if (courseRepository.findByName(courseDto.getName()).isPresent()) {
            throw new RuntimeException("Course name already exists");
        }

        if (courseDto.getStartDate().isAfter(courseDto.getEndDate())) {
            throw new RuntimeException("End date must be after start date");
        }

        // Create and save the course entity
        Course course = new Course();
        course.setId(course.getId());
        course.setName(courseDto.getName());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());

        courseRepository.save(course);
    }
}

