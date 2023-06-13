package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  List<Teacher> findByDepartmentId(Long departmentId);

  @Query("SELECT ts.teacher FROM TeacherSubject ts WHERE ts.subject.id = :subjectId")
  List<Teacher> findBySubjectId(@Param("subjectId") Long subjectId);

  @Query("SELECT ts.teacher FROM TeacherSubject ts WHERE ts.subject.id = :subjectId AND ts.teacher.department.id = :departmentId")
  List<Teacher> findByDepartmentIdAndSubjectId(@Param("departmentId") Long departmentId, @Param("subjectId") Long subjectId);



}
