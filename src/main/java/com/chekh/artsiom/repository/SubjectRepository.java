package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByDepartmentId(Long departmentId);

    List<Subject> findAll();

    List<Subject> findByIdIn(List<Long> ids);
}
