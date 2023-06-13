package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.DepartmentStudent;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.StudentSubject;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.DepartmentStudentRepository;
import com.chekh.artsiom.repository.StudentRepository;
import com.chekh.artsiom.repository.StudentSubjectRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private StudentSubjectRepository studentSubjectRepository;

  @Autowired
  private DepartmentStudentRepository departmentStudentRepository;


  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public List<Student> findByDepartmentId(Long departmentId) {
    return studentRepository.findByDepartmentId(departmentId);
  }

  @Override
  public List<Student> findBySubjectId(Long subjectId) {
    return studentRepository.findBySubjectId(subjectId);
  }

  @Override
  public List<Student> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId) {
    return studentRepository.findByDepartmentIdAndSubjectId(departmentId, subjectId);
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

  @Override
  public List<Subject> getSubjectsByStudentId(Long studentId) {
    List<StudentSubject> studentSubjects = studentSubjectRepository.findByStudentId(studentId);
    List<Subject> subjects = new ArrayList<>();
    for (StudentSubject studentSubject : studentSubjects) {
      subjects.add(studentSubject.getSubject());
    }
    return subjects;
  }

  @Override
  public List<Department> getDepartmentsByStudentId(Long studentId) {
    List<DepartmentStudent> studentDepartments = departmentStudentRepository.findByStudentId(
        studentId);
    List<Department> departments = new ArrayList<>();
    for (DepartmentStudent studentDepartment : studentDepartments) {
      departments.add(studentDepartment.getDepartment());
    }
    return departments;
  }

  @Transactional
  public void saveStudentWithSubjects(Student student, List<Long> subjectIds) {


    Long studentId = studentRepository.save(student).getId();

    studentSubjectRepository.deleteByStudentId(studentId);

    if (subjectIds != null && !subjectIds.isEmpty()) {
      for (Long subjectId : subjectIds) {

        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        StudentSubject studentSubject = new StudentSubject(student, subject);
        studentSubjectRepository.save(studentSubject);
      }
    }
  }

  @Override
  public void saveStudentWithDepartments(Student student, List<Long> departmentIds) {

  }

  @Transactional
  public void saveStudent(Student student, List<Long> subjectIds, List<Long> departmentIds) {

    student = studentRepository.save(student);
    Long studentId = student.getId();

    // Сохраняем связи между студентом и кафедрами
    if (departmentIds != null) {
      for (Long departmentId : departmentIds) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
          DepartmentStudent departmentStudent = new DepartmentStudent(department, student);
          departmentStudentRepository.save(departmentStudent);
        }
      }
    }

    if (subjectIds != null) {
      for (Long subjectId : subjectIds) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        if (subject != null) {
          StudentSubject studentSubject = new StudentSubject(student, subject);
          studentSubjectRepository.save(studentSubject);
        }
      }
    }
  }

//  @Transactional
//  @Override
//  public void saveStudent(Student student, List<Long> subjectIds, List<Long> departmentIds) {
//
//    student = studentRepository.save(student); // сохраняем студента и получаем обновленный объект из базы данных
//    Long studentId = student.getId(); // получаем идентификатор сохраненного студента
//
//    if (departmentIds != null && !departmentIds.isEmpty()) {
//      for (Long departmentId : departmentIds) {
//        Department department = departmentRepository.findById(departmentId).orElse(null);
//        DepartmentStudent departmentStudent = new DepartmentStudent(department, student);
//        departmentStudent.getId().setDepartmentId(departmentId); // устанавливаем идентификатор кафедры
//        departmentStudentRepository.save(departmentStudent);
//      }
//    }
//
//    if (subjectIds != null && !subjectIds.isEmpty()) {
//      for (Long subjectId : subjectIds) {
//        Subject subject = subjectRepository.findById(subjectId).orElse(null);
//        StudentSubject studentSubject = new StudentSubject(student, subject);
//        studentSubject.getId().setStudentId(studentId); // устанавливаем идентификатор студента
//        studentSubjectRepository.save(studentSubject);
//      }
//    }
//  }
}



