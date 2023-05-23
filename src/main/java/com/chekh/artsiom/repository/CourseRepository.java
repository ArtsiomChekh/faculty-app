package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Course;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

  List<Course> findAllByDepartmentId(Long departmentId);
  List<Course> findAllByTeacherId(Long teacherId);
}
