package com.learn.management.service;

import com.learn.management.DTO.LogEntryDto;
import com.learn.management.DTO.StudentRegistrationDto;
import com.learn.management.model.Course;
import com.learn.management.model.LogEntry;
import com.learn.management.model.Student;
import com.learn.management.repository.CourseRepository;
import com.learn.management.repository.LogEntryRepository;
import com.learn.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private static StudentRepository studentRepository;

    private static LogEntryRepository logEntryRepository;

    private static CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.logEntryRepository = logEntryRepository;
        this.courseRepository = courseRepository;
    }

    public static void registerStudent(@NotNull StudentRegistrationDto registrationDto) {
        // Validate business rules
        if (studentRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email address already registered");
        }
        LocalDate dob = registrationDto.getDob();
        int age = LocalDate.now().getYear() - dob.getYear();
        if (age < 16) {
            throw new RuntimeException("Student must be at least 16 years old");
        }

        // Create and save the student entity
        Student student = new Student();
        student.setId(student.getId());
        student.setFirstName(registrationDto.getFirstName());
        student.setLastName(registrationDto.getLastName());
        student.setDob(dob);
        student.setAddress(registrationDto.getAddress());
        student.setEmail(registrationDto.getEmail());
        student.setPhoneNumber(registrationDto.getPhoneNumber());

        studentRepository.save(student);
    }

    public void logHours(Long studentId, LogEntryDto logEntryDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        validateLogEntry(student, logEntryDto);

        LogEntry logEntry = new LogEntry();
        logEntry.setId(logEntry.getId());
        logEntry.setStudent(student);
        logEntry.setDate(logEntryDto.getDate());
        logEntry.setCategory(logEntryDto.getCategory());
        logEntry.setDescription(logEntryDto.getDescription());
        logEntry.setTimeSpent(logEntryDto.getTimeSpent());

        logEntryRepository.save(logEntry);
    }


    public void selectCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        validateCourseSelection(student);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public List<LogEntry> getLogEntries(Long studentId) {
        // Retrieve log entries for the given student
        return logEntryRepository.findByStudentId(studentId);
    }

    public void updateLog(Long studentId, Long logEntryId, LogEntryDto logEntryDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LogEntry logEntry = logEntryRepository.findById(logEntryId)
                .orElseThrow(() -> new RuntimeException("Log entry not found"));

        // Validate that the log entry belongs to the student
        validateLogOwnership(student, logEntry);

        // Update log entry fields
        updateLogEntryFields(logEntry, logEntryDto);

        logEntryRepository.save(logEntry);
    }

    public void deleteLog(Long studentId, Long logEntryId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LogEntry logEntry = logEntryRepository.findById(logEntryId)
                .orElseThrow(() -> new RuntimeException("Log entry not found"));

        // Validate that the log entry belongs to the student
        validateLogOwnership(student, logEntry);

        // Remove log entry from the student's logEntries list
        student.getLogEntries().remove(logEntry);

        // Delete the log entry
        logEntryRepository.delete(logEntry);
    }

    private void validateCourseSelection(Student student) {
        // Validate business rule: Student should not select more than three courses
        if (student.getCourses().size() >= 3) {
            throw new RuntimeException("Student cannot register for more than three courses");
        }
    }

    private void validateLogEntry(Student student, LogEntryDto logEntryDto) {
        // Validate business rules for logging hours
        LocalDate today = LocalDate.now();
        if (!logEntryDto.getDate().equals(today)) {
            throw new RuntimeException("Log entry date must be today");
        }

        // Check if the student has already logged hours for the same date
        List<LogEntry> existingEntries = logEntryRepository.findByStudentIdAndDate(
                student.getId(), logEntryDto.getDate());

        if (!existingEntries.isEmpty()) {
            throw new RuntimeException("Student has already logged hours for today");
        }
    }

    private void validateLogOwnership(Student student, LogEntry logEntry) {
        // Validate that the log entry belongs to the student
        if (!student.getLogEntries().contains(logEntry)) {
            throw new RuntimeException("Log entry does not belong to the student");
        }
    }

    private void updateLogEntryFields(LogEntry logEntry, LogEntryDto logEntryDto) {
        // Update log entry fields
        logEntry.setId(logEntry.getId());
        logEntry.setDate(logEntryDto.getDate());
        logEntry.setCategory(logEntryDto.getCategory());
        logEntry.setDescription(logEntryDto.getDescription());
        logEntry.setTimeSpent(logEntryDto.getTimeSpent());


    }

}
