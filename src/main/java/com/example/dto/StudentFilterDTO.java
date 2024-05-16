package com.example.dto;

import com.example.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
public class StudentFilterDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private GenderEnum Gender;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
}
