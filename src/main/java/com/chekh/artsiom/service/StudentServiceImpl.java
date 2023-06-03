package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.StudentRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private TeacherRepository teacherRepository;

  // Returns a list of all students
  @Override
  public List<Student> getAllStudents() {
    // Use the student repository to find all students
    return studentRepository.findAll();
  }

  // Returns a list of students for a specified department
  @Override
  public List<Student> findByDepartmentId(Long departmentId) {
    // Use the student repository to find all students belonging to the department with the specified departmentId
    return studentRepository.findAllByDepartments_Id(departmentId);
  }

  // Returns a list of students for a specified subject
  @Override
  public List<Student> findBySubjectId(Long subjectId) {
    // Use the student repository to find all students enrolled in the course with the specified subjectId
    return studentRepository.findAllBySubjects_Id(subjectId);
  }

  // Returns a list of students for a specified department and subject
  @Override
  public List<Student> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId) {
    return studentRepository.findByDepartments_IdAndSubjects_Id(departmentId, subjectId);
  }

  // Returns a map of subjects and teachers for a specified student
  @Override
  public Map<Subject, Set<Teacher>> getSubjectsAndTeachersByStudentId(Long studentId) {
    // Find the student with the specified studentId
    Optional<Student> optionalStudent = studentRepository.findById(studentId);

    // If the student exists, create a map of subjects and teachers for the student
    if (optionalStudent.isPresent()) {
      Student student = optionalStudent.get();
      Map<Subject, Set<Teacher>> result = new HashMap<>();
      for (Subject subject : student.getSubjects()) {
        Set<Teacher> teachers = subject.getTeachers();
        result.put(subject, teachers);
      }
      return result;
    } else {
      // If the student does not exist, throw an exception
      throw new IllegalArgumentException("Student with id " + studentId + " not found");
    }
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



