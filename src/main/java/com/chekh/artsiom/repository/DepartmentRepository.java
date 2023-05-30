package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  // This method signature defines a query that returns all departments sorted in descending order by the number of teachers associated with them.
  List<Department> findAllByOrderByTeacherCountDesc();

  // This method signature defines a query that returns all departments sorted in descending order by the number of students associated with them.
  List<Department> findAllByOrderByStudentCountDesc();
}
