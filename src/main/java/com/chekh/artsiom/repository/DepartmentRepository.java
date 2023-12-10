package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d.id, d.name, count(distinct ds.student) AS studentCount, COUNT(distinct t.id) AS teacherCount"
            + " FROM Department d"
            + " LEFT JOIN DepartmentStudent ds ON d = ds.department"
            + " LEFT JOIN Student s ON s = ds.student"
            + " LEFT JOIN Teacher t ON d = t.department"
            + " GROUP BY d")
    List<Object[]> findDepartmentStudentTeacherCount();
}
