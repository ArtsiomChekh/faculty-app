package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.StudentRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private TeacherRepository teacherRepository;

  // список всех студентов
  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  //  список студентов по каждой кафедре
  @Override
  public List<Student> findByDepartmentId(Long departmentId) {
    return studentRepository.findAllByDepartments_Id(departmentId);
  }

  //  список студентов по каждому предмету
  @Override
  public List<Student> findBySubjectId(Long subjectId) {
    return studentRepository.findAllBySubjects_Id(subjectId);

  }

  @Override
  public List<Student> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId) {
    return studentRepository.findByDepartments_IdAndSubjects_Id(departmentId,subjectId);
  }

  // добавить студента на факультет
  @Override
  public Student addStudent(Student student) {

    return studentRepository.save(student);
    }



  // студент: список своих предметов и преподователей
  @Override
  public Map<Subject, Set<Teacher>> getSubjectsAndTeachersByStudentId(Long studentId) {
    Optional<Student> optionalStudent = studentRepository.findById(studentId);
    if (optionalStudent.isPresent()) {
      Student student = optionalStudent.get();
      Map<Subject, Set<Teacher>> result = new HashMap<>();
      for (Subject subject : student.getSubjects()) {
        Set<Teacher> teachers = subject.getTeachers();
        result.put(subject, teachers);
      }
      return result;
    } else {
      throw new IllegalArgumentException("Student with id " + studentId + " not found");
    }
  }




  // преподователь: список своих студентов
  }



