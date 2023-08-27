package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  @Query(
      "SELECT d.id, d.name, count(distinct ds.student) AS studentCount, COUNT(distinct t.id) AS teacherCount\n"
          + "  FROM Department d\n"
          + "  LEFT JOIN DepartmentStudent ds ON d.id = ds.department\n"
          + "  LEFT JOIN Student s ON s.id = ds.student\n"
          + "  LEFT JOIN Teacher t ON d.id = t.department"
          + "  GROUP BY d.id, d.name")
  List<Object[]> findDepartmentStudentTeacherCount();
}
