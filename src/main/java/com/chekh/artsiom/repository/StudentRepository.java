package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("SELECT ds.student FROM DepartmentStudent ds WHERE ds.department.id = :departmentId")
  List<Student> findByDepartmentId(@Param("departmentId") Long departmentId);

  @Query("SELECT ss.student FROM StudentSubject ss WHERE ss.subject.id = :subjectId")
  List<Student> findBySubjectId(@Param("subjectId") Long subjectId);

  @Query("SELECT s FROM Student s JOIN DepartmentStudent ds ON s.id = ds.student.id JOIN StudentSubject ss ON s.id = ss.student.id WHERE ds.department.id = :departmentId AND ss.subject.id = :subjectId")
  List<Student> findByDepartmentIdAndSubjectId(@Param("departmentId") Long departmentId,
      @Param("subjectId") Long subjectId);

}
