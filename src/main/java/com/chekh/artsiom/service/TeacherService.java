package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface TeacherService {

  List<Teacher> getAllTeachers();

  List<Teacher> findByDepartmentId(Long departmentId);

  List<Teacher> findBySubjectId(Long subjectId);

  List<Teacher> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId);


  void saveTeacher(Teacher teacher);

  void deleteTeacherById(long id);
  void deleteTeacherSubjectsByTeacherId(long teacherId);
  Teacher getTeacherById(long id);

  void saveTeacherWithSubjects(Teacher teacher, List<Long> subjectIds);

  List<Subject> getSubjectsByTeacherId(Long teacherId);


}

