package com.learn.management.repository;

import com.learn.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    String FIND_BY_EMAIL = "SELECT * FROM student WHERE email = :email";



    Optional<Student> findByEmail(String email);



}
