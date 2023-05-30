package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Subject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

  // This method signature defines a query that returns all subjects belonging to the department with the specified departmentId.
  List<Subject> findAllByDepartmentId(Long departmentId);

  // This method signature defines a query that returns all subjects taught by the teacher with the specified teacherId
  List<Subject> findAllByTeachersId(Long teacherId);

  // This method signature defines a query that returns all subjects.
  List<Subject> findAll();

  List<Subject> findByIdIn(List<Long> ids);
}
