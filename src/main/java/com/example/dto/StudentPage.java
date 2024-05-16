package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentPage<T> {
    private List<T> studentList;
    private Long totalCount;
}
