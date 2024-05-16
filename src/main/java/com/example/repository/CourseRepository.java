package com.example.repository;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.enums.GenderEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity,Integer>, PagingAndSortingRepository<CourseEntity,Integer> {

    List<CourseEntity> findByNameOrPriceOrDuration(String name,  Double price, LocalDate duration);
    List<CourseEntity> findByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
    List<CourseEntity> findByPriceBetween(Double fromPrice, Double ToPrice);
    List<CourseEntity> findByCreatedDate(LocalDate date);
    Page<CourseEntity> findByStudentIdAndCourseIdAndMarkFromAndMarkToAndCreatedDateFromAndCreatedDateToAndStudentNameAndCourseName(Integer studentId, Integer courseId, String markFrom, String markTo, LocalDate createdDateFrom, LocalDate createdDateTo, String studentName, String courseName, Pageable pageable);


//    PageImpl<CourseDTO> findBylOrderById(int level, PageRequest pageRequest);
}
