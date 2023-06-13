package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  List<Department> findByIdIn(List<Long> ids);
}
