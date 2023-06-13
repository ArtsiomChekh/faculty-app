package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import java.util.List;


public interface StudentService {

  List<Student> getAllStudents();

  List<Student> findByDepartmentId(Long departmentId);

  List<Student> findBySubjectId(Long subjectId);

  List<Student> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId);

  Student getStudentById(Long studentId);

  void saveStudent(Student student);

  void deleteStudentById(long id);

  List<Subject> getSubjectsByStudentId(Long studentId);

  List<Department> getDepartmentsByStudentId(Long studentId);

  void saveStudentWithSubjects(Student student, List<Long> subjectIds);

  void saveStudentWithDepartments(Student student, List<Long> departmentIds);

  void saveStudent(Student student, List<Long> subjectIds, List<Long> departmentIds);

//  void saveStudent(Student student, List<Long> subjectIds, List<Long> departmentIds);
}
