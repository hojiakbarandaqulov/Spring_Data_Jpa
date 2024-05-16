package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.GenderEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {
//    List<StudentEntity> findByName(String name); // select * from student where name = ?

//    Optional<StudentEntity> findByPhone(String phone); // select * from student where phone = ?

//    List<StudentEntity> findByNameAndSurname(String name,String surname); // select * from student where name = ?
//    @Query(" from StudentEntity s where s.name = :name and s.surname = :surname and s.level=:level and s.age=:age and s.gender=:gender and s.created_date=:created_date")
    List<StudentEntity> findByNameOrSurnameOrLevelOrAgeOrGenderOrCreatedDate(String name, String surname, Integer level, Integer age, GenderEnum gender, LocalDate createdDate);
    // select * from student where name = ? and surname = ?
//    @Query("select s from StudentEntity s where s.created_date=:createdDate")
    List<StudentEntity> findByCreatedDate(LocalDate createdDate);

//    @Query("select s from StudentEntity s where s.created_date between :fromDate and :toDate")
    List<StudentEntity> findByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
//    @Query("select s from StudentEntity s where s.level=:level order by s.id")
    Page<StudentEntity> findByLevelOrderById(Integer level, PageRequest pageRequest);

    Page<StudentEntity> findByIdAndNameAndSurnameAndLevelAndAgeAndGenderAndCreatedDateFromAndCreatedDateTo(Integer id, String name, String surname, Integer level, Integer age, GenderEnum gender, LocalDate createdDateFrom, LocalDate createdDateTo, Pageable pageable);

//    List<StudentEntity> findByNameOrSurnameOrLevelOrAgeOrGenderOrCreatedDate(String name, String surname, Integer level, String age, GenderEnum gender, LocalDate createdDate);


//    List<StudentEntity> findByAgeBetween(Integer from, Integer to);
    // select * from student where age between ? and ?

//    List<StudentEntity> findByBirthdateAfter(LocalDate birthdate);
    // select * from student where birthdate > ?

//    List<StudentEntity> findByBirthdateAfterAndName(LocalDate birthdate, String name);
    // select * from student where birthdate > ? and name = ?

//    List<StudentEntity> findByNameLike(String name); // select * from student where name like ?

}
