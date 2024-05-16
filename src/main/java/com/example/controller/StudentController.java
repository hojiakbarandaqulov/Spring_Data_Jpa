package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.dto.StudentFilterDTO;
import com.example.enums.GenderEnum;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<StudentDTO> all() {
        return studentService.getAll();
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<StudentDTO> byId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(studentService.byId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO response = studentService.create(studentDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        studentService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/name/{name}/{surname}/{level}/{age}/{gender}/{created_date}")
    public ResponseEntity<List<StudentDTO>> getByNameOrSurnameOrLevelOrAgeOrGender(@PathVariable("name") String name,
                                                                                   @PathVariable("surname") String surname,
                                                                                   @PathVariable("level") Integer level,
                                                                                   @PathVariable("age") Integer age,
                                                                                   @PathVariable("gender") GenderEnum gender, @PathVariable("created_date") LocalDate createdDate) {
        List<StudentDTO> studentDTOList = studentService.getByNameOrSurnameOrLevelOrAgeOrGender(name, surname, level, age, gender, createdDate);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/givenDate/{created_date}")
    public ResponseEntity<List<StudentDTO>> getCreatedDate(@PathVariable("created_date") LocalDate createdDate) {
        List<StudentDTO> studentDTOList = studentService.getByCreatedDate(createdDate);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/between/{from_date}/{to_date}")
    public ResponseEntity<List<StudentDTO>> getBetweenCreatedDate(@PathVariable("from_date") LocalDate fromDate,
                                                                  @PathVariable("to_date") LocalDate toDate) {
        List<StudentDTO> studentDTOList = studentService.getByDateBetween(fromDate, toDate);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<StudentDTO>> pageable(@RequestParam(value = "page", defaultValue = "1") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<StudentDTO> studentDTOList = studentService.pagination(page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/pagination/{level}")
    public ResponseEntity<PageImpl<StudentDTO>> getPageableLevel(@PathVariable Integer level,
                                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<StudentDTO> studentList = studentService.paginationLevelSort(page, size, level);
        return ResponseEntity.ok().body(studentList);
//        return studentService.paginationLevelSort(level,page,size);
    }

    @GetMapping("/pagination/{created_date}")
    public ResponseEntity<PageImpl<StudentDTO>> pageableCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                                    @PathVariable LocalDate created_date) {
        PageImpl<StudentDTO> studentList = studentService.paginationCreatedDate(page - 1, size);
        return ResponseEntity.ok().body(studentList);
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<StudentDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody StudentFilterDTO filter) {
        PageImpl<StudentDTO> studentList = studentService.filterWithPagination(filter.getId(),filter.getName(),
                filter.getSurname(), filter.getLevel(),
                filter.getAge(), filter.getGender(),
                filter.getCreatedDateFrom(),
                filter.getCreatedDateTo(), page - 1, size);
        return ResponseEntity.ok().body(studentList);
    }
}
