package com.example.entity;


import com.example.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter

@Entity
@Table(name = "student")

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "level")
    private Integer level;
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;
    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "created_date_from")
    private LocalDate createdDateFrom;
    @Column(name = "created_date_to")
    private LocalDate createdDateTo;


    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseEntity> courseSet;
}
