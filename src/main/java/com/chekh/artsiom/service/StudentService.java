package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentService {

  List<Student> getAllStudents();
  List<Student> findByDepartmentId(Long departmentId);

  List<Student> findBySubjectId(Long subjectId);

  List<Student> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId);

  Map<Subject, Set<Teacher>> getSubjectsAndTeachersByStudentId(Long studentId);



}
