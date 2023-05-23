package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;
import java.util.Set;

public interface TeacherService {

    List<Teacher> getAllTeachers();
    List<Teacher> getTeachersByDepartment(Long departmentId);
    List<Teacher> getTeachersBySubject(Long subjectId);
    Teacher addTeacher(Teacher teacher);

    Set<Subject> getDepartmentSubjects(Teacher teacher);

    List<Teacher> findAllByDepartment(Teacher teacher);


  }

