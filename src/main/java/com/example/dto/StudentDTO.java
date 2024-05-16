package com.example.dto;

import com.example.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private GenderEnum gender;
    private LocalDate createdDate;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
}
