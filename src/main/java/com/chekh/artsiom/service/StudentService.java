package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Teacher;
import java.util.List;

public interface StudentService {

  List<Student> getAllStudents();
  List<Student> getStudentsByDepartment(Long departmentId);
  List<Student> getStudentsByCourse(Long courseId);

  Student addStudent(Student student);
  List<Student> findAllByDepartment(Teacher teacher);

}
