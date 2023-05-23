package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Course;
import com.chekh.artsiom.model.Student;
import java.util.List;

public interface CourseService {

  List<Course> getAllCourses();
  List<Course> getCoursesByDepartment(Long departmentId);

  Course addCourse(Course course);

  List<Course> getCoursesByTeacher(Long teacherId);
}
