package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Course;
import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import java.util.List;
import java.util.Set;

public interface TeacherService {

    List<Teacher> getAllTeachers();
    List<Teacher> getTeachersByDepartment(Long departmentId);
    List<Teacher> getTeachersByCourse(Long courseId);
    Teacher addTeacher(Teacher teacher);

    Set<Course> getDepartmentCourses(Teacher teacher);

    List<Teacher> findAllByDepartment(Teacher teacher);


  }

