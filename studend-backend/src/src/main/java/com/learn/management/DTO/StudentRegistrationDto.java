package com.learn.management.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRegistrationDto {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String email;
    private String phoneNumber;

    // getters and setters


}
