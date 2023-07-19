package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT ds.department, COUNT(ds.student) FROM DepartmentStudent ds GROUP BY ds.department")
    List<Object[]> getDepartmentStudentCount();

}
