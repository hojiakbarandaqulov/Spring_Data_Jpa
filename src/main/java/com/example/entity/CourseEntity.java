package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "course")

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "studentId")
    private Integer studentId;
    @Column(name = "courseId")
    private Integer courseId;
    @Column(name = "markFrom")
    private String markFrom;
    @Column(name = "markTo")
    private String markTo;
    @Column(name = "createdDateFrom")
    private LocalDate createdDateFrom;
    @Column(name = "createdDateTo")
    private LocalDate createdDateTo;
    @Column(name = "studentName")
    private String studentName;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "duration")
    private LocalDate duration;
    @Column(name = "createdDate")
    private LocalDate createdDate;
}
