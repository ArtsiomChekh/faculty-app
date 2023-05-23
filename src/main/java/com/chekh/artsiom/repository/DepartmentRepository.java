package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findAllByOrderByTeacherCountDesc();
  List<Department> findAllByOrderByStudentCountDesc();
}
