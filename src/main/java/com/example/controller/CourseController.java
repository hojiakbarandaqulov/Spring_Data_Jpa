package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.CourseFilterDTO;
import com.example.dto.StudentDTO;
import com.example.dto.StudentFilterDTO;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/course")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO courseDTO) {
        CourseDTO response = courseService.create(courseDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<CourseDTO> byId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(courseService.byId(id));
    }

    @GetMapping("/all")
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody CourseDTO dto) {
        courseService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        courseService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/name/{name}/{price}/{duration}")
    public ResponseEntity<List<CourseDTO>> getByNameOrSurnameOrLevelOrAgeOrGender(@PathVariable("name") String name,
                                                                                  @PathVariable("price") Double price,
                                                                                  @PathVariable("duration") LocalDate duration) {
        List<CourseDTO> courseDTOList = courseService.findByNameOrPriceOrDuration(name, price, duration);
        return ResponseEntity.ok().body(courseDTOList);
    }

    @GetMapping("/between/{from_price}/{to_price}")
    public ResponseEntity<List<CourseDTO>> getBetweenCreatedPrice(@PathVariable("from_date") Double fromPrice,
                                                                  @PathVariable("to_date") Double toPrice) {
        List<CourseDTO> courseList = courseService.getByPriceBetween(fromPrice, toPrice);
        return ResponseEntity.ok().body(courseList);
    }

    @GetMapping("/between_date/{from_date}/{to_date}")
    public ResponseEntity<List<CourseDTO>> getBetweenCreatedDate(@PathVariable("from_date") LocalDate fromDate,
                                                                 @PathVariable("to_date") LocalDate toDate) {
        List<CourseDTO> courseList = courseService.getByDateBetween(fromDate, toDate);
        return ResponseEntity.ok().body(courseList);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<CourseDTO>> pageable(@RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<CourseDTO> courseList = courseService.pagination(page - 1, size);
        return ResponseEntity.ok().body(courseList);
    }

  /*  @GetMapping("/created_date/{level}")
    public ResponseEntity<PageImpl<CourseDTO>> pageableCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                                   @PathVariable Integer level) {
        PageImpl<CourseDTO> courseList = courseService.paginationLevelSort(page, size, level);
        return ResponseEntity.ok().body(courseList);
    }*/

    @GetMapping("/created_date/{created_date}")
    public ResponseEntity<PageImpl<CourseDTO>> pageableCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                                   @PathVariable("created_date") LocalDate created_date) {
        PageImpl<CourseDTO> courseList = courseService.getByCreatedDate(created_date, page - 1, size);
        return ResponseEntity.ok().body(courseList);
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<CourseDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody CourseFilterDTO filter) {
        PageImpl<CourseDTO> courseList = courseService.filterWithPagination(filter.getStudentId(),filter.getCourseId(),
                filter.getMarkFrom(), filter.getMarkTo(),
                filter.getCreatedDateFrom(), filter.getCreatedDateTo(),
                filter.getStudentName(),
                filter.getCourseName(), page - 1, size);
        return ResponseEntity.ok().body(courseList);
    }
}

