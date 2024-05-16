package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private Integer id;
    private String name;
    private Double price;
    private LocalDate duration;
    private LocalDate createdDate;

    private Integer studentId;
    private Integer courseId;
    private String markFrom;
    private String markTo;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private String studentName;
    private String courseName;
}
