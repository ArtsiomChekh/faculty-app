package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.DepartmentStudent;
import com.chekh.artsiom.model.DepartmentStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentStudentRepository extends JpaRepository<DepartmentStudent, DepartmentStudentId> {

    void deleteByStudentId(Long studentId);

    List<DepartmentStudent> findByStudentId(Long studentId);
}
