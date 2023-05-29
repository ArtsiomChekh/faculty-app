package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;
import java.util.Set;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    List<Teacher> findByDepartmentId(Long departmentId);
    List<Teacher> findBySubjectId(Long subjectId);

    List<Teacher> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId);

    Teacher addTeacher(Teacher teacher);

//    Set<Subject> getDepartmentSubjects(Teacher teacher);

    List<Teacher> findAllByDepartment(Teacher teacher);


  void saveTeacher(Teacher teacher);

    void deleteTeacherById(long id);

  Teacher getTeacherById(long id);
}

