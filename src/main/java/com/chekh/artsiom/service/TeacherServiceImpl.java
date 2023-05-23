package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Course;
import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.CourseRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private CourseRepository courseRepository;


  // список всех преподавателей
  @Override
  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  //  список преподавателей по каждой кафедре
  @Override
  public List<Teacher> getTeachersByDepartment(Long departmentId) {
    return teacherRepository.findAllByDepartmentId(departmentId);
  }

  // список преподавателей по каждому предмету
  @Override
  public List<Teacher> getTeachersByCourse(Long courseId) {
    return teacherRepository.findAllByCourses_Id(courseId);
  }
  // добавить преподавателя на кафедру
  @Override
  public Teacher addTeacher(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  // преподователь: список предметов своей кафедры
  @Override
  public Set<Course> getDepartmentCourses(Teacher teacher) {
    Set<Course> departmentCourses = new HashSet<>();
    Department department = teacher.getDepartment();

    if (department != null) {
      Set<Course> courses = department.getCourses();
      for (Course course : courses) {
        if (course.getTeacher().equals(teacher)) {
          departmentCourses.add(course);
        }
      }
    }

    return departmentCourses;
  }

  // преподователь: список преподавателей своей кафедры
  @Override
  public List<Teacher> findAllByDepartment(Teacher teacher) {
    List<Teacher> teachers = teacherRepository.findAllByDepartmentId(teacher.getDepartment().getId());
    // упорядочиваем список преподавателей по фамилии
    teachers.sort(Comparator.comparing(Teacher::getLastName));
    return teachers;
  }

  // Преподаватель может * взяться вести предмет кафедры


}
