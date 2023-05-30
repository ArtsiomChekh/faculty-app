package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  // This method signature defines a query that returns all teachers working in the department with the specified departmentId.
  List<Teacher> findAllByDepartmentId(Long departmentId);

  // This method signature defines a query that returns all teachers who teach the course with the specified courseId.
  List<Teacher> findAllBySubjects_Id(Long courseId);

  // This method signature defines a query that returns all teachers who work in the department with the specified departmentId and teach the course with the specified subjectId.
  List<Teacher> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId);

}
