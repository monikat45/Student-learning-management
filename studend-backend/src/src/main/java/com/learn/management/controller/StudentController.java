package com.learn.management.controller;

import com.learn.management.DTO.LogEntryDto;
import com.learn.management.DTO.StudentRegistrationDto;
import com.learn.management.model.LogEntry;
import com.learn.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {


   @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDto registrationDto) {
        try {
            StudentService.registerStudent(registrationDto);
            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<?> selectCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            studentService.selectCourse(studentId, courseId);
            return new ResponseEntity<>("Course selection successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{studentId}/log")
    public ResponseEntity<?> logHours(@PathVariable Long studentId, @RequestBody @Valid LogEntryDto logEntryDto) {
        try {
            studentService.logHours(studentId, logEntryDto);
            return new ResponseEntity<>("Log entry successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{studentId}/logs")
    public ResponseEntity<List<LogEntry>> getLogEntries(@PathVariable Long studentId) {
        List<LogEntry> logEntries = studentService.getLogEntries(studentId);
        return new ResponseEntity<>(logEntries, HttpStatus.OK);
    }

    @PutMapping("/{studentId}/logs/{logEntryId}")
    public ResponseEntity<?> updateLog(
            @PathVariable Long studentId, @PathVariable Long logEntryId, @RequestBody @Valid LogEntryDto logEntryDto) {
        try {
            studentService.updateLog(studentId, logEntryId, logEntryDto);
            return new ResponseEntity<>("Log entry updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{studentId}/logs/{logEntryId}")
    public ResponseEntity<?> deleteLog(@PathVariable Long studentId, @PathVariable Long logEntryId) {
        try {
            studentService.deleteLog(studentId, logEntryId);
            return new ResponseEntity<>("Log entry deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
