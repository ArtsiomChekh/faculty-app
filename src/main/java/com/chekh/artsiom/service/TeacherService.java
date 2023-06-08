package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface TeacherService {

  List<Teacher> getAllTeachers();

  List<Teacher> findByDepartmentId(Long departmentId);

  List<Teacher> findBySubjectId(Long subjectId);

  List<Teacher> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId);

  void saveTeacher(Teacher teacher);

  Long saveTeacherWithSubjects(Teacher teacher, List<Long> subjectIds, Department department);

  void deleteTeacherById(long id);

  Teacher getTeacherById(long id);

  List<Subject> getTeacherSubjects(Teacher teacher);

  void addTeacherSubject(Teacher teacher, Subject subject);
}

