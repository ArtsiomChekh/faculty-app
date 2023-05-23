package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  List<Teacher> findAllByDepartmentId(Long departmentId);
  List<Teacher> findAllByCourses_Id(Long courseId);




}
