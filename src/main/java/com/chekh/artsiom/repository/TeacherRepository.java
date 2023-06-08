package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  List<Teacher> findAllByDepartmentId(Long departmentId);

//  List<Teacher> findAllBySubjectId(Long subjectId);

  @Query("SELECT ts.teacher FROM TeacherSubject ts JOIN ts.teacher t JOIN ts.subject s WHERE t.id = :departmentId AND s.id = :subjectId")
  List<Teacher> findByDepartmentIdAndSubjectId(@Param("departmentId") Long departmentId, @Param("subjectId") Long subjectId);


  @Query("SELECT ts.teacher FROM TeacherSubject ts WHERE ts.subject.id = :subjectId")
  List<Teacher> findAllBySubjectId(@Param("subjectId") Long subjectId);

  @Query("SELECT ts.subject FROM TeacherSubject ts WHERE ts.teacher = :teacher")
  List<Subject> getTeacherSubjects(@Param("teacher") Teacher teacher);



}
