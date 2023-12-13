package com.learn.management.repository;

import com.learn.management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    String FIND_BY_NAME = "SELECT * FROM course WHERE name = :name";
    
    Optional<Course> findByName(String name);


}
