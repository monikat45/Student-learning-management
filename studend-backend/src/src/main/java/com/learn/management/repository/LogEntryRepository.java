package com.learn.management.repository;

import com.learn.management.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {



    List<LogEntry> findByStudentIdAndDate(Long id, LocalDate date);

    List<LogEntry> findByStudentId(Long studentId);
}
