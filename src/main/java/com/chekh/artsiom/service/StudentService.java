package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import java.util.List;


public interface StudentService {

  List<Student> getAllStudents();

  List<Student> findByDepartmentId(Long departmentId);

  List<Student> findBySubjectId(Long subjectId);

  List<Student> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId);


  Student getStudentById(Long studentId);

  void saveStudent(Student student);

  void deleteStudentById(long id);
}
