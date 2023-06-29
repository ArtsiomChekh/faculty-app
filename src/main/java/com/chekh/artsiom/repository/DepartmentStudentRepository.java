package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.DepartmentStudent;
import com.chekh.artsiom.model.DepartmentStudentId;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentStudentRepository extends
    JpaRepository<DepartmentStudent, DepartmentStudentId> {

  void deleteByStudentId(Long studentId);

  List<DepartmentStudent> findByStudentId(Long studentId);



}
