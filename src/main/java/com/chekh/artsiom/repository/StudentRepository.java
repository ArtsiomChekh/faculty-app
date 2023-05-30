package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

 // This method signature defines a query that returns all students who are enrolled in a course with the specified courseId.
 List<Student> findAllBySubjects_Id(Long courseId);

 // This method signature defines a query that returns all students who are enrolled in a department with the specified departmentId.
 List<Student> findAllByDepartments_Id(Long departmentId);

 // This method signature defines a query that returns all students who are enrolled in a department with the specified departmentId and are also enrolled in a course with the specified subjectId.
 List<Student> findByDepartments_IdAndSubjects_Id(Long departmentId, Long subjectId);

}
