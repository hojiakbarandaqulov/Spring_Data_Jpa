package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.GenderEnum;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public StudentDTO byId(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLevel(entity.getLevel());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


    public StudentDTO create(StudentDTO studentDTO) {
        StudentEntity entity = new StudentEntity();
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setLevel(studentDTO.getLevel());
        entity.setAge(studentDTO.getAge());
        entity.setGender(studentDTO.getGender());
        entity.setCreatedDate(studentDTO.getCreatedDate());
        studentRepository.save(entity);
        studentDTO.setId(entity.getId());
        return studentDTO;
    }

    public Boolean update(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLevel(dto.getLevel());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(dto.getCreatedDate());
        studentRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
//        studentRepository.deleteById(id);
        StudentEntity student = get(id);
        studentRepository.delete(student);
        return true;
    }

    public StudentEntity get(Integer id) {
        /*Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }
        return optional.get();*/
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public List<StudentDTO> getByNameOrSurnameOrLevelOrAgeOrGender(String name, String surname, Integer level, Integer age, GenderEnum gender, LocalDate createdDate) {
        List<StudentEntity> entityList = studentRepository.findByNameOrSurnameOrLevelOrAgeOrGenderOrCreatedDate(name,surname,level,age,gender,createdDate);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public List<StudentDTO> getByCreatedDate(LocalDate createdDate){
        List<StudentEntity> entityList=studentRepository.findByCreatedDate(createdDate);
        List<StudentDTO> dtoList=new LinkedList<>();
        for (StudentEntity entity:entityList){
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<StudentDTO> getByDateBetween(LocalDate fromDate, LocalDate toDate){
        String OneDat= String.valueOf(fromDate);
        String TwoDate= String.valueOf(toDate);
        List<StudentEntity> entityList=studentRepository.findByCreatedDateBetween(fromDate,toDate);
        List<StudentDTO> dtoList=new LinkedList<>();
        for (StudentEntity entity:entityList){
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
  /*  public StudentDTO getByPhone(String phone) {
        Optional<StudentEntity> optional = studentRepository.findByPhone(phone);

        if (optional.isEmpty()) {
            return null;
        }
        StudentEntity entity = optional.get();

        StudentDTO dto = new StudentDTO();
        // set

        return dto;
    }*/
    public PageImpl<StudentDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("age").ascending());
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }
    public PageImpl<StudentDTO> paginationLevelSort(int level, int page, int size) {
      /*  PageRequest pageRequest = PageRequest.of(page, size);
        return studentRepository.findByLevelOrderById(level,pageRequest);*/
//        Page<StudentEntity> pageObj = studentRepository.findByLevelOrderById(level);

        Pageable pageable = PageRequest.of(page, size, Sort.by(String.valueOf(level)).ascending());
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Long totalCount = pageObj.getTotalElements();
        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }

    public PageImpl<StudentDTO> paginationCreatedDate(int page, int size){
        Pageable pageable=PageRequest.of(page,size,Sort.by("created_date").ascending());
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }

    public PageImpl<StudentDTO> filterWithPagination(Integer id, String name, String surname, Integer level,Integer age, GenderEnum gender, LocalDate createdDateFrom, LocalDate createdDateTo, int page, int size) {
        Pageable pageable=PageRequest.of(page,size,Sort.by("id").descending());
        Page<StudentEntity> pageObj = studentRepository.findByIdAndNameAndSurnameAndLevelAndAgeAndGenderAndCreatedDateFromAndCreatedDateTo(id,name,surname,level,age,gender,createdDateFrom,createdDateTo, pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDateFrom(entity.getCreatedDateFrom());
            dto.setCreatedDateTo(entity.getCreatedDateTo());
            dtoList.add(dto);
        }
        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }

}
