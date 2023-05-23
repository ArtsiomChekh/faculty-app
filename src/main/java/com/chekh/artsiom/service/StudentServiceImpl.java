package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.StudentRepository;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

  @Autowired
  private StudentRepository studentRepository;

  // список всех студентов
  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  //  список студентов по каждой кафедре
  @Override
  public List<Student> getStudentsByDepartment(Long departmentId) {
    return studentRepository.findAllByDepartmentId(departmentId);
  }

  //  список студентов по каждому предмету
  @Override
  public List<Student> getStudentsByCourse(Long courseId) {
    return studentRepository.findAllByCourses_Id(courseId);

  }

  // добавить студента на факультет
  @Override
  public Student addStudent(Student student) {

    return studentRepository.save(student);
    }

  @Override
  public List<Student> findAllByDepartment(Teacher teacher) {
    List<Student> students = studentRepository.findAllByDepartmentId(teacher.getDepartment().getId());
    // упорядочиваем список студентов по фамилии
    students.sort(Comparator.comparing(Student::getLastName));
    return students;
  }

  // преподователь: список своих студентов

}
