package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentService {

  List<Student> getAllStudents();
  List<Student> getStudentsByDepartment(Long departmentId);
  List<Student> getStudentsBySubject(Long subjectId);

  Student addStudent(Student student);
  List<Student> findAllByDepartment(Teacher teacher);
  Map<Subject, Set<Teacher>> getSubjectsAndTeachersByStudentId(Long studentId);

//  Set<Subject> getSubjectsByStudentId(Long studentId);
//  Set<Teacher> getTeachersByStudentId(Long studentId);



}
