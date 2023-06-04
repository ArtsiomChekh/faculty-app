package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public List<Student> findByDepartmentId(Long departmentId) {
    return studentRepository.findAllByDepartments_Id(departmentId);
  }

  @Override
  public List<Student> findBySubjectId(Long subjectId) {
    return studentRepository.findAllBySubjects_Id(subjectId);
  }

  @Override
  public List<Student> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId) {
    return studentRepository.findByDepartments_IdAndSubjects_Id(departmentId, subjectId);
  }

  @Override
  public Student getStudentById(Long studentId) {
    return studentRepository.findById(studentId).orElse(null);
  }

  @Override
  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  @Override
  public void deleteStudentById(long id) {
    studentRepository.deleteById(id);
  }
}



