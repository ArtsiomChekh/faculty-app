package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Subject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

  List<Subject> findAllByDepartmentId(Long departmentId);

  List<Subject> findAllByTeachersId(Long teacherId);

  List<Subject> findAll();

  List<Subject> findByIdIn(List<Long> ids);
}
