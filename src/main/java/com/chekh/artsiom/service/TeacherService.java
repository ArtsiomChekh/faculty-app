package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;

public interface TeacherService {
  List<Teacher> getAllTeachers();

  List<Teacher> findByDepartmentId(Long departmentId);

  List<Teacher> findBySubjectId(Long subjectId);

  List<Teacher> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId);

  void saveTeacher(Teacher teacher);

  void deleteTeacherById(long id);

  Teacher getTeacherById(long id);

  void saveTeacherWithSubjects(Teacher teacher, List<Long> subjectIds);

  List<Subject> getSubjectsByTeacherId(Long teacherId);
}

