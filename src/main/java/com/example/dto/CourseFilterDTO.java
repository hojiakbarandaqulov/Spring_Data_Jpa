package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter

public class CourseFilterDTO {
    private Integer studentId;
    private Integer courseId;
    private String markFrom;
    private String markTo;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private String studentName;
    private String courseName;
}
