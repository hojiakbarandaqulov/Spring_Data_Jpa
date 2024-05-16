package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.enums.GenderEnum;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO courseDTO) {
        CourseEntity entity = new CourseEntity();

        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreatedDate(courseDTO.getCreatedDate());
        courseRepository.save(entity);
        courseDTO.setId(entity.getId());
        return courseDTO;
    }

    public CourseDTO byId(Integer id) {
        CourseEntity entity = get(id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(entity.getId());
        courseDTO.setName(entity.getName());
        courseDTO.setPrice(entity.getPrice());
        courseDTO.setDuration(entity.getDuration());
        courseDTO.setCreatedDate(entity.getCreatedDate());
        return courseDTO;
    }

    private CourseEntity get(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> entity = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity course : entity) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            courseDTO.setPrice(course.getPrice());
            courseDTO.setDuration(course.getDuration());
            courseDTO.setCreatedDate(course.getCreatedDate());
            dtoList.add(courseDTO);
        }
        return dtoList;
    }

    public Boolean update(Integer id, CourseDTO dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(dto.getCreatedDate());
        courseRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;
    }

    public List<CourseDTO> findByNameOrPriceOrDuration(String name, Double price, LocalDate duration) {
        List<CourseEntity> entityList = courseRepository.findByNameOrPriceOrDuration(name, price, duration);
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<CourseDTO> getByPriceBetween(Double fromPrice, Double toPrice) {
        List<CourseEntity> entityList = courseRepository.findByPriceBetween(fromPrice, toPrice);
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<CourseDTO> getByDateBetween(LocalDate fromDate, LocalDate toDate) {
        List<CourseEntity> entityList = courseRepository.findByCreatedDateBetween(fromDate, toDate);
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {

            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public PageImpl<CourseDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
        return getStudentDTO(pageable);
    }

    /* public PageImpl<CourseDTO> paginationCreatedDate(LocalDate created_date){
         Pageable pageable=PageRequest.of(Sort.by(String.valueOf(created_date)).ascending());
         return getStudentDTO(pageable);
     }*/
    public PageImpl<CourseDTO> getByCreatedDate(LocalDate created_date, int page, int size) {
        List<CourseEntity> entityList = courseRepository.findByCreatedDate(created_date);
        Pageable pageable = PageRequest.of(page, size, Sort.by("created_date").descending());
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());

            entity.setCreatedDate(LocalDate.now());
            dto.setCreatedDate(entity.getCreatedDate());


        }
        return (PageImpl<CourseDTO>) dtoList;
    }

    private PageImpl<CourseDTO> getStudentDTO(Pageable pageable) {
        Page<CourseEntity> pageObj = courseRepository.findAll(pageable);
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : pageObj.getContent()) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Long totalCount = pageObj.getTotalElements();
        return new PageImpl<CourseDTO>(dtoList, pageable, totalCount);
    }

    public PageImpl<CourseDTO> filterWithPagination(Integer studentId, Integer courseId, String markFrom, String markTo, LocalDate createdDateFrom, LocalDate createdDateTo, String studentName, String courseName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<CourseEntity> pageObj = courseRepository.findByStudentIdAndCourseIdAndMarkFromAndMarkToAndCreatedDateFromAndCreatedDateToAndStudentNameAndCourseName(studentId, courseId, markFrom, markTo, createdDateFrom, createdDateTo, studentName, courseName, pageable);

        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : pageObj.getContent()) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMarkFrom(dto.getMarkFrom());
            dto.setMarkTo(entity.getMarkTo());
            dto.setCreatedDateFrom(entity.getCreatedDateFrom());
            dto.setCreatedDateTo(entity.getCreatedDateTo());
            dto.setCreatedDateFrom(entity.getCreatedDateFrom());
            dto.setStudentName(entity.getStudentName());
            dto.setCourseName(entity.getCourseName());
            dtoList.add(dto);
        }
        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<CourseDTO>(dtoList, pageable, totalCount);
    }
}
      /*  public PageImpl<CourseDTO> paginationLevelSort(int level, int page, int size) {
            PageRequest pageRequest = PageRequest.of(page,size);
            return courseRepository.findBylOrderById(level,pageRequest);
    }*/
/*
    public PageImpl<CourseDTO> getByCreatedDate(Integer level) {

    }*/
   /* public PageImpl<CourseDTO> getByLevelPagination(Integer level) {
        List<CourseEntity> entityList = courseRepository.findByCreatedDate(created_date);
        Pageable pageable = PageRequest.of(page, size, Sort.by("created_date").descending());
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());

            entity.setCreatedDate(LocalDate.now());
            dto.setCreatedDate(entity.getCreatedDate());


        }
        return (PageImpl<CourseDTO>) dtoList;
    }*/