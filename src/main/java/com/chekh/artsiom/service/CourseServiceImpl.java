package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Course;
import com.chekh.artsiom.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

  @Autowired
  CourseRepository courseRepository;

  // список всех предметов
  @Override
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  // список предметов по каждой кафедре
  @Override
  public List<Course> getCoursesByDepartment(Long departmentId) {
    return courseRepository.findAllByDepartmentId(departmentId);
  }

  // добавить предмет для кафедру
  @Override
  public Course addCourse(Course course) {
    return courseRepository.save(course);
  }

  // преподователь: список своих предметов
  @Override
  public List<Course> getCoursesByTeacher(Long teacherId) {
    return courseRepository.findAllByTeacherId(teacherId);
  }
}
